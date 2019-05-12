package com.sensedia.notification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class ApixNotificationKotlinApplication

fun main(args: Array<String>) {
	runApplication<ApixNotificationKotlinApplication>(*args)
}