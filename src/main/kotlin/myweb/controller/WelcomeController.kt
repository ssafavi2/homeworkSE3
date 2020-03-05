package myweb.controller

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
class WelcomeController {

    // inject via application.properties
    @Value("\${welcome.message}")
    private val message: String? = null


    @GetMapping("/")
    fun main(model: Model): String {
        model.addAttribute("message", message)

        return "welcome" //view
    }

    // /hello?name=kotlin
    @GetMapping("/hello")
    fun mainWithParam(
        @RequestParam(name = "name", required = false, defaultValue = "")
        name: String, model: Model
    ): String {

        model.addAttribute("message", name)

        return "welcome" //view
    }

}