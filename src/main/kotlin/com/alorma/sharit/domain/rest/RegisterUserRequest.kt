package com.alorma.sharit.domain.rest;

data class RegisterUserRequest(val name: String,
                               val login: String,
                               val email: String,
                               val token: String)