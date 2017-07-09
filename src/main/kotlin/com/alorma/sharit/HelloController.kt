package com.alorma.sharit

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class HelloController {

    @RequestMapping("/")
    fun index(): String {
        return "Greetings from SharIt! Spring Boot"
    }

    @RequestMapping(value = "/register",
            method = arrayOf(RequestMethod.POST),
            consumes = arrayOf("application/json"),
            produces = arrayOf("application/json")
    )
    @ResponseBody
    fun registerUser(@RequestBody registerUserRequest: RegisterUserRequest)
            : ResponseEntity<RegisteredUserResponse> {
        val response = RegisteredUserResponse(true)
        return ResponseEntity.ok(response)
    }

}
