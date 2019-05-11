package com.sensedia.notification.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import java.util.Optional

@Configuration
class TwilioConfiguration(

    @Value("\${twilio.accountSid}")
    val accountSid: String,

    @Value("\${twilio.authToken}")
    val authToken: String,

    @Value("\${twilio.from}")
    val from: String
)