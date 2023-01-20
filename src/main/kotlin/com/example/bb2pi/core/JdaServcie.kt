package com.example.bb2pi.core

import mu.KotlinLogging
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.OnlineStatus
import org.springframework.stereotype.Component

@Component
class JdaServcie {
    private val logger = KotlinLogging.logger {}
    fun JdaServcie() {
        val builder = JDABuilder.createDefault("MTA2NTg2MjgzNDY1NjkwMzE5OA.GvnbFe.q3C0qyXj3pNJWU71IiXCbyFQBixJYU4vIonllM")
        builder.setAutoReconnect(true)
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB)
        try {
            val jda = builder.build();
        } catch (e: Exception) {
            logger.error("bot build failed", e)
        }
    }
}