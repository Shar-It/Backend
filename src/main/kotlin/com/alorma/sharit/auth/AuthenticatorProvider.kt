package com.alorma.sharit.auth;

import com.alorma.sharit.domain.AppUser
import com.alorma.sharit.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AuthenticatorProvider @Autowired constructor(val repository: UserRepository) {

    fun getAuthUser(token: String): AppUser? {
        return repository.findByToken(token)
    }


}