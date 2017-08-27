package com.alorma.sharit.controller

import com.alorma.sharit.domain.AppUser
import com.alorma.sharit.domain.rest.RegisterUserRequest
import com.alorma.sharit.domain.rest.RestUser
import com.alorma.sharit.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(val repository: UserRepository) {

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody re: RegisterUserRequest) {
        val appUser = AppUser(re.login, re.name, re.token, re.email)
        repository.insert(appUser)
    }

    @GetMapping
    @RequestMapping("/search")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    fun searchUser(@RequestParam("query") query: String) = query

    @GetMapping
    fun list() = repository.findAll().map { RestUser(it.login, it.name, it.email) }
}