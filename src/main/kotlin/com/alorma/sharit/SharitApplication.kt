package com.alorma.sharit

import com.alorma.sharit.exception.UnauthorizedException
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletResponse

@SpringBootApplication
@ComponentScan("com.alorma.sharit")
@EnableAutoConfiguration
class SharitApplication

fun main(args: Array<String>) {
    SpringApplication.run(SharitApplication::class.java, *args)

    @ExceptionHandler
    fun handleIllegalArgumentException(e: UnauthorizedException, response: HttpServletResponse) {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}
