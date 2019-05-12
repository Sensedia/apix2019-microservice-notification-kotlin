package com.sensedia.notification.components

import com.sensedia.notification.service.TwilioService
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RabbitMQConsumer {

    var logger = LoggerFactory.getLogger(RabbitMQConsumer::class.java)

    @Autowired
    lateinit var twilioService: TwilioService

    @RabbitListener(queues = ["apix-notification-queue"])
    fun receive(payload: ByteArray) {
        val charset = Charsets.UTF_8
        val message = payload.toString(charset)

        logger.info(" >>> Received '$message'")
        logger.info(" >>> Sending to Twilio...")

        twilioService.send(message, "+5519995473669")
        logger.info(" >>> Message sent successfully !!!")
    }
}