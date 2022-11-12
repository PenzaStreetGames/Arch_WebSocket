package com.penzastreet.spring_web_socket.controllers

import com.penzastreet.spring_web_socket.models.Message
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class EchoController {

    @MessageMapping("/webs")
    @SendTo("/topic/echo")
    fun echo(message: Message): Message {
        return message
    }

}
