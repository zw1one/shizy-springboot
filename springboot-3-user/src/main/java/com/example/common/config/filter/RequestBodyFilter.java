package com.example.common.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 重新组装 HttpServletRequest 返回, 解决拦截器中从流中获取完 post 请求中的 body 参数，controller 层无法再次获取的问题
 */
@Slf4j
public class RequestBodyFilter implements Filter {

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            String method = httpServletRequest.getMethod();
            String contentType = httpServletRequest.getContentType() == null ? "" : httpServletRequest.getContentType();
            // 如果是POST请求并且不是文件上传
            if (HttpMethod.POST.name().equals(method) && !contentType.equals(MediaType.MULTIPART_FORM_DATA_VALUE)) {
                // 重新生成ServletRequest  这个新的 ServletRequest 获取流时会将流的数据重写进流里面
                requestWrapper = new RequestReaderHttpServletRequestWrapper((HttpServletRequest) request);
            }
        }
        if (requestWrapper == null) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(requestWrapper, response);
        }
    }

//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//    }


    /**
     * 解决拦截器从流中获取完整的 body 请求参数后，无法再次调用流中数据的问题，否则报以下错误信息
     * <p>
     * I/O error while reading input message; nested exception is java.io.IOException: Stream closed
     */
    class RequestReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

        private final byte[] body;

        public RequestReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            body = inputStream2String(request.getInputStream()).getBytes(StandardCharsets.UTF_8);
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {

            final ByteArrayInputStream bais = new ByteArrayInputStream(body);

            return new ServletInputStream() {

                @Override
                public int read() throws IOException {
                    return bais.read();
                }

                @Override
                public boolean isFinished() {
                    return false;
                }

                @Override
                public boolean isReady() {
                    return false;
                }

                @Override
                public void setReadListener(ReadListener readListener) {

                }
            };
        }

        /**
         * 将 inputStream 里的数据读取出来并转换成字符串
         *
         * @param inputStream inputStream
         * @return String
         */
        public String inputStream2String(InputStream inputStream) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                sb.append("get body params fail");
                log.error(e.getMessage());
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        log.error(e.getMessage());
                    }
                }
            }
            return sb.toString();
        }
    }

}
