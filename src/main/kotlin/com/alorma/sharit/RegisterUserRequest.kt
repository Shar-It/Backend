package com.alorma.sharit;

data class RegisterUserRequest(val name: String,
                               val login: String,
                               val email: String,
                               val token: String)