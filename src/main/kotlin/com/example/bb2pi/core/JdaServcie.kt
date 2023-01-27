package com.example.bb2pi.core

import mu.KotlinLogging
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.requests.GatewayIntent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration

class JdaServcie(
    private val botListenerAdaptor: BotListenerAdaptor
) {
    private val logger = KotlinLogging.logger {}
    @Bean
    fun getBot(): JDA {
        val builder = JDABuilder.createDefault("MTA2NTg2MjgzNDY1NjkwMzE5OA.GNntm2.a3ZEW4a7FAk544JFALYlKcG0WGs2_QGBkjc8Q8")
        builder.setAutoReconnect(true)
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB)

        try {
            logger.info("===== bot build success")

        } catch (e: Exception) {
            logger.error("bot build failed", e)
        }
        val bot = builder.build()
        bot.addEventListener(botListenerAdaptor)
        bot.awaitReady()

        return bot
    }
}