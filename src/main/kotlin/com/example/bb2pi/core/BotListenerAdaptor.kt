package com.example.bb2pi.core

import mu.KotlinLogging
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.springframework.stereotype.Component

@Component
class BotListenerAdaptor : ListenerAdapter() {
    private val logger = KotlinLogging.logger{}

    override fun onMessageReceived(event: MessageReceivedEvent) {
        logger.info("event.message: {}", event.message)
        logger.info("message received: {}", event.message.contentRaw)
        logger.info("message channel: {}", event.channel)

        // 받은 메세지 내용이 !ping이라면
        if (event.message.contentRaw == "!ping") {
            // pong라는 내용을 보낸다.
            event.channel.sendMessage("pong!").queue()
        }
    }

}