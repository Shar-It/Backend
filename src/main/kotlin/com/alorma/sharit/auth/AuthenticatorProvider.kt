package com.alorma.sharit.auth;

import com.alorma.sharit.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class AuthenticatorProvider @Autowired constructor(val repository: UserRepository) {

    fun isAuthenticated(token: String): Boolean {
        repository.findByToken(token)?.let {
            return true
        }

        return false
    }


}