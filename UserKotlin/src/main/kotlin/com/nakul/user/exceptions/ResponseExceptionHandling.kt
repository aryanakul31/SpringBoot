package com.nakul.user.exceptions

import com.nakul.user.dto.BaseResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
object ResponseExceptionHandling {

    @ExceptionHandler
    fun handle(exception: Exception): ResponseEntity<BaseResponse<Nothing>> {
        exception.printStackTrace()
        val httpResponse = when (exception) {
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
            message = httpResponse.reasonPhrase,
        )
        return ResponseEntity(response, httpResponse)
    }

}
