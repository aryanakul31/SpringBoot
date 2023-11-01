package com.nakul.user.misc

import com.nakul.user.dto.BaseResponse
import org.springframework.core.MethodParameter
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@ControllerAdvice
class CustomResponseAdvise : ResponseBodyAdvice<Any> {
    override fun supports(returnType: MethodParameter, converterType: Class<out HttpMessageConverter<*>>): Boolean =
        true

    override fun beforeBodyWrite(
        body: Any?,
        returnType: MethodParameter,
        selectedContentType: MediaType,
        selectedConverterType: Class<out HttpMessageConverter<*>>,
        request: ServerHttpRequest,
        response: ServerHttpResponse
    ): Any? {
        if (!returnType.containingClass.isAnnotationPresent(RestController::class.java)) return body
        if (returnType.method?.isAnnotationPresent(IgnoreResponseBinding::class.java) == true) return body


        return BaseResponse(response = body)
    }

}