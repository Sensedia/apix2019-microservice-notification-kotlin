package com.sensedia.notification.configuration

import com.google.gson.JsonParser
import com.rabbitmq.client.ConnectionFactory
import com.rabbitmq.client.Delivery
import com.sensedia.notification.service.TwilioService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.nio.charset.Charset
import javax.annotation.PostConstruct


@Configuration
class RabbitMqConfiguration(

        @Value("\${rabbitmq.host}")
        val host: String,

        @Value("\${rabbitmq.queueName}")
        val queueName: String

) {

    var logger = LoggerFactory.getLogger(RabbitMqConfiguration::class.java)

    @Autowired
    lateinit var twilioService: TwilioService

    @PostConstruct
    fun initConsumer() {
        val factory = ConnectionFactory()
        factory.host = host

        val connection = factory.newConnection()
        val channel = connection.createChannel()

        val QUEUE_NAME = queueName


        logger.info(" >>> Waiting for messages")

        val deliverCallback = { consumerTag: String, delivery: Delivery ->
            val json = String(delivery.body, Charset.forName("UTF-8"))
            val jsonObj = JsonParser().parse(json).asJsonObject

            val phone = jsonObj.get("phone")
            val numberOfCombinations = jsonObj.get("numberOfCombinationsFound").asInt

            val message = if (numberOfCombinations > 0)
                "There are $numberOfCombinations combinations available for the requested kit. To visualize them, access PobreFit."
            else "Unfortunately we haven't any combinations for the requested kit."

            logger.info(" >>> Sending to Twilio...")

            twilioService.send(message, phone.asString)
            logger.info(" >>> Message sent successfully !!!")

        }

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, { consumerTag -> })
    }
}