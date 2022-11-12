package com.penzastreet.spring_web_socket.models

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "key_value")
data class KeyValue (
    @Id
    @Column(name = "key")
    val key: String,
    @Column(name = "value")
    var value: String
    )