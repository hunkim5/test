package com.airpremia.eosh.config.support;

import java.util.Objects;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class BasicArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {

        return (
            parameter.getParameterType().equals(Long.class) && Objects.requireNonNull(parameter.getParameterName())
                .equalsIgnoreCase("loginUserSeq"));

    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter, ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        if (parameter.getParameterType().equals(Long.class) && Objects.requireNonNull(parameter.getParameterName())
            .equalsIgnoreCase("loginUserSeq")) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null) {
                String loginUserSeqStr = "dummyUserLoginId";
                return Long.valueOf(loginUserSeqStr);
            }
        }

        return null;
    }

}
