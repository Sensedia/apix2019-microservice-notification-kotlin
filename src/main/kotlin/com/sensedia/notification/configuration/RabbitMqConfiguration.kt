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

    @Autowired
    lateinit var twilioService: TwilioService

    @PostConstruct
    fun initConsumer() {
        val factory = ConnectionFactory()
        factory.host = host

        val connection = factory.newConnection()
        val channel = connection.createChannel()

        val QUEUE_NAME = queueName
        channel.queueDeclare(QUEUE_NAME, true, false, false, null)

        println(" [*] Waiting for messages")

        val deliverCallback = { consumerTag: String, delivery: Delivery ->
            val to = delivery.properties.headers["to"].toString()

            val message = String(delivery.body, Charset.forName("UTF-8"))

            logger.info(" >>> Received '$message'")
            logger.info(" >>> Sending to Twilio...")

            twilioService.send(message, to)
            logger.info(" >>> Message sent successfully !!!")

        }

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, { consumerTag -> })
    }
}