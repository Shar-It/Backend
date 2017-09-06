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
import org.springframework.social.facebook.api.Facebook
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(val repository: UserRepository,
                                            val authProvider: AuthenticatorProvider,
                                            val facebook: Facebook) {

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
    fun searchUser(@RequestHeader(HttpHeaders.AUTHORIZATION) authorizationHeader: String?,
                   @RequestParam("query") query: String): List<RestUser> {
        authorizationHeader?.let {
            val authUser = authProvider.getAuthUser(authorizationHeader)
            authUser?.let {
                return repository.findById(query)
                        .union(repository.findByName(query))
                        .union(repository.findByEmail(query))
                        .distinctBy { it.login }
                        .filterNot { it.login == authUser.login }
                        .map { RestUser(it.login, it.name, it.email) }
            }
        }

        throw UnauthorizedException()
    }

    @GetMapping
    fun list(@RequestHeader(HttpHeaders.AUTHORIZATION) auth: String?): List<RestUser> {
        auth?.let {
            if (authProvider.getAuthUser(auth) != null) {
                return repository.findAll().map { RestUser(it.login, it.name, it.email) }
            } else {
                throw UnauthorizedException()
            }
        }

        throw UnauthorizedException()
    }

    @ExceptionHandler
    fun handleAuthException(e: UnauthorizedException, response: HttpServletResponse) {
        response.sendError(HttpStatus.UNAUTHORIZED.value());
    }
}