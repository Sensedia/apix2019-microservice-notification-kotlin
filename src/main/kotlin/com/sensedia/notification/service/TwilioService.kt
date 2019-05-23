package com.sensedia.notification.service

import com.sensedia.notification.configuration.TwilioConfiguration
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URI
import java.util.Arrays

@Service
class TwilioService {

    @Autowired
    private lateinit var twilioConfiguration: TwilioConfiguration

    /**
     * to (eg: +5519994984326)
     */
    fun send(body: String, to: String) {
        Twilio.init(twilioConfiguration.accountSid, twilioConfiguration.authToken)
        val message = Message.creator(
                PhoneNumber(to),
                PhoneNumber(twilioConfiguration.from),
                body).create()

        System.out.println(message.sid)
    }

}