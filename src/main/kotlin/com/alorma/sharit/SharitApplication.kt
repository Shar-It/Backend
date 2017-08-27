package com.alorma.sharit

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.alorma.sharit")
@EnableAutoConfiguration
class SharitApplication

fun main(args: Array<String>) {
    SpringApplication.run(SharitApplication::class.java, *args)
}
