package com.example.common.config.resolver;

import com.example.common.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;

/**
 * https://www.jianshu.com/p/627636f77109
 * 该写法会优先使用@RequestBody的解析器，除非删除注解。不适用
 */
@Deprecated
    public class GlobalArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
//        return parameter.getParameterType().equals(Student.class);
        return true;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {

        Class apiEntity = parameter.getParameterType();
        Map<String, String[]> paramMap = webRequest.getParameterMap();
        if(true){
            throw new MyException("zz");
        }

        HandlerMethodArgumentResolverComposite resolver = applicationContext.getBean(HandlerMethodArgumentResolverComposite.class);
        return resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
    }
}
