package com.alorma.sharit.controller

import com.alorma.sharit.exception.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@RestController
class ErrorController {

    @ExceptionHandler
    fun handleAuthException(e: UnauthorizedException, response: HttpServletResponse) {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}