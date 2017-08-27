package com.alorma.sharit.controller

import com.alorma.sharit.auth.AuthenticatorProvider
import com.alorma.sharit.domain.AppUser
import com.alorma.sharit.domain.rest.RegisterUserRequest
import com.alorma.sharit.domain.rest.RestUser
import com.alorma.sharit.exception.UnauthorizedException
import com.alorma.sharit.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(val repository: UserRepository, val authProvider: AuthenticatorProvider) {

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    fun registerUser(@RequestBody re: RegisterUserRequest) {
        val appUser = AppUser(re.login, re.name, re.token, re.email)
        repository.insert(appUser)
    }

    @GetMapping
    @RequestMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    fun searchUser(@RequestHeader(HttpHeaders.AUTHORIZATION) auth: String?,
                   @RequestParam("query") query: String): List<RestUser> {
        auth?.let {
            if (authProvider.isAuthenticated(auth)) {
                return repository.findById(query)
                        .union(repository.findByName(query))
                        .union(repository.findByEmail(query))
                        .distinctBy { it.login }
                        .map { RestUser(it.login, it.name, it.email) }
            } else {
                throw UnauthorizedException()
            }
        }

        throw UnauthorizedException()
    }

    @GetMapping
    fun list(@RequestHeader(HttpHeaders.AUTHORIZATION) auth: String?): List<RestUser> {
        auth?.let {
            if (authProvider.isAuthenticated(auth)) {
                return repository.findAll().map { RestUser(it.login, it.name, it.email) }
            } else {
                throw UnauthorizedException()
            }
        }

        throw UnauthorizedException()
    }
}