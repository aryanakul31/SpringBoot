package com.nakul.user.misc

import com.nakul.user.dto.BaseResponse
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice


@ControllerAdvice
class CustomResponseAdvise : ResponseBodyAdvice<Any> {

    @ExceptionHandler
    fun handle(exception: Exception): ResponseEntity<BaseResponse<*>> {
        exception.printStackTrace()
        val httpResponse = when (exception) {
            is MethodArgumentNotValidException -> {
                val errors: MutableMap<String, String?> = HashMap()
                exception.bindingResult.allErrors.forEach { error ->
                    val fieldName = (error as FieldError).field
                    val message = error.getDefaultMessage()
                    errors[fieldName] = message
                }

                val response = BaseResponse(
                    response = errors,//(exception.bindingResult.allErrors[0] as FieldError).field,
                    status = HttpStatus.BAD_REQUEST.value(),
                    message = "",
                )

                return ResponseEntity(response, HttpStatus.BAD_REQUEST)
            }

            is NoSuchElementException -> HttpStatus.NOT_FOUND
            is CustomException -> {
                val response = BaseResponse(
                    response = null,
                    status = exception.httpStatus.value(),
                    message = exception.errorMessage,
                )
                return ResponseEntity(response, exception.httpStatus)
            }

            else -> {
                HttpStatus.BAD_REQUEST
            }
        }
        val response = BaseResponse(
            response = null,
            status = httpResponse.value(),
            message = exception.localizedMessage ?: exception.message ?: "",
        )
        return ResponseEntity(response, httpResponse)
    }

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