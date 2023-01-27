package com.example.bb2pi.core

import mu.KotlinLogging
import net.dv8tion.jda.api.events.GenericEvent
import net.dv8tion.jda.api.hooks.EventListener
import org.springframework.stereotype.Component

@Component
class BotEventListener : EventListener {
    private val logger = KotlinLogging.logger() {}

    override fun onEvent(event: GenericEvent) {
        logger.info("=== event executed {}", event.rawData)

    }


}