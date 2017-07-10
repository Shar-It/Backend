package com.alorma.sharit

import org.springframework.web.bind.annotation.*

@RestController
class HelloController {

    @RequestMapping("/")
    fun index(): String {
        return "Greetings from SharIt! Spring Boot"
    }
}
