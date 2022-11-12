package com.penzastreet.spring_web_socket.controllers

import com.penzastreet.spring_web_socket.models.KeyValue
import com.penzastreet.spring_web_socket.models.Message
import com.penzastreet.spring_web_socket.services.KeyValueService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class KeyValueController(private val service: KeyValueService) {

    @MessageMapping("/kv/set")
    @SendTo("/topic/kv/set")
    fun setKeyValue(kv: KeyValue): KeyValue {
        return service.setKeyValue(kv)
    }

    @MessageMapping("/kv/delete")
    @SendTo("/topic/kv/delete")
    fun getByKey(kv: KeyValue) {
        service.deleteKeyValueByKey(kv.key)
    }

    @MessageMapping("/kv/items")
    @SendTo("/topic/kv/items")
    fun getItems(): List<KeyValue> {
        return service.getKeyValueSet()
    }

}