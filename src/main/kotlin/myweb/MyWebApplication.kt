package myweb

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MyWebApplication


fun main(args: Array<String>) {
    runApplication<MyWebApplication>(*args)
}