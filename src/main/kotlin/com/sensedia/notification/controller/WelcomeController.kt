package com.sensedia.notification.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
class WelcomeController {

    @GetMapping("/welcome/{name}")
    fun send(@PathVariable name: String): String {
        return "Hello $name, welcome to APIX Notification !! Today is " + Date()
    }
}