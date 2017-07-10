package com.alorma.sharit.domain

import org.springframework.data.annotation.Id

data class AppUser(@Id val login: String,
                   val name: String,
                   val token: String,
                   val email: String)