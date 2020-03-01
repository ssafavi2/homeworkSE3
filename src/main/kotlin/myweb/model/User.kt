package com.loyaltyone.myweb.model


import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "users")
class User {

    @get:Id
    @get:GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    @get:Column(name = "username", nullable = false)
    lateinit var userName: String
    @get:Column(name = "metadata", nullable = false)
    lateinit var metaData: String
    @get:Column(name = "date_time", nullable = false)
    var dateTime: LocalDateTime = LocalDateTime.now()


    constructor() {

    }

    constructor(userName:String, metaData: String) {
        this.metaData = metaData
        this.userName = userName

    }
}