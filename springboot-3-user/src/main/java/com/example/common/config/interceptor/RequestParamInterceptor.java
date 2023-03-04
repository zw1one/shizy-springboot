package com.example.common.config.interceptor;

import com.example.utils.http.StreamUtil;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * https://juejin.cn/post/6962053059241312293
 */
public class RequestParamInterceptor extends HandlerInterceptorAdapter {

    /**
     * 请求前
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) handler;
//            Class<?>[] parameterTypes = handlerMethod.getMethod().getParameterTypes();
//
//            String a = StreamUtil.read(request.getInputStream());
//
//
//
//            MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
//            for (MethodParameter methodParameter : methodParameters) {
//                RequestBody annotation = methodParameter.getParameterAnnotation(RequestBody.class);
//                if (annotation != null) {
//                    String paramName = methodParameter.getParameterName();
//                    String paramValue = request.getParameter(paramName);
//                    // 在这里对参数进行处理
//                    Map paramMap = request.getParameterMap();
//                    System.out.println();
//                }
//            }
//        }

        return true;
    }

    /**
     * 请求后
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * finally
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
