package com.sensedia.notification.configuration

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

    val MESSAGE = "There are combinations available for the requested kit. To visualize them, access PobreFit."

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
            val phone = String(delivery.body, Charset.forName("UTF-8"))

            logger.info(" >>> Received '$MESSAGE'")
            logger.info(" >>> Sending to Twilio...")

            twilioService.send(MESSAGE, phone)
            logger.info(" >>> Message sent successfully !!!")

        }

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, { consumerTag -> })
    }
}