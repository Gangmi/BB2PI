package com.example.bb2pi

import mu.KotlinLogging
import net.dv8tion.jda.api.JDABuilder
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class Bb2PiApplication
    private val logger = KotlinLogging.logger {}
    fun main(args: Array<String>) {
        logger.info {
            "application start"
        }

        SpringApplication.run(Bb2PiApplication::class.java, *args)
    }


