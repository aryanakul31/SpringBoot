package com.nakul.user.exceptions

import org.springframework.http.HttpStatus

open class CustomException(val httpStatus: HttpStatus, val errorMessage: String) : Exception()

class UserNotFoundException : CustomException(httpStatus = HttpStatus.BAD_REQUEST, errorMessage = "User Not Found")
class EmailAlreadyExistsException :
    CustomException(httpStatus = HttpStatus.BAD_REQUEST, errorMessage = "Email already exists")

class InvalidPasswordException : CustomException(httpStatus = HttpStatus.BAD_REQUEST, errorMessage = "Invalid Password")
