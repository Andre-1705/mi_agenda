package com.example.miagendaweb

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MiAgendaWebApplication

fun main(args: Array<String>) {
    println("Spring Boot arranco correctamente")
    SpringApplication.run(MiAgendaWebApplication::class.java, *args)
}
