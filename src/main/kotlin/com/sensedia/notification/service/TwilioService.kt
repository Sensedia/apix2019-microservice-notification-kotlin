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
                body)
            .setMediaUrl(
                Arrays.asList(URI.create("https://d9hhrg4mnvzow.cloudfront.net/www.apix.com.br/1b64d6cd-logo-white_05v02g05s02e000000001.png")))
            .create()

        System.out.println(message.sid)
    }

}