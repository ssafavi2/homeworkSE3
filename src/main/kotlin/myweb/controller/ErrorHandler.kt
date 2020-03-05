package myweb.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleUserNotFoundException(error: UserNotFoundException): ErrorResponse {
        return ErrorResponse(error.code, error.message)
    }

    @ExceptionHandler(UserEmptyException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleUserEmptyException(error: UserEmptyException): ErrorResponse {
        return ErrorResponse(error.code, error.message)
    }

}