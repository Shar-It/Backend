package com.alorma.sharit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
open class ObjectMapperConfiguration {
    @Bean
    @Primary
    open fun objectMapper() = ObjectMapper().apply {
        registerModule(KotlinModule())
    }
}