package com.loyaltyone.myweb.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping
class UserController {
    @GetMapping("/{user}")
    @ResponseBody
    fun returnTheUserName(@PathVariable("user") user: String):String
    {
        return user
    }
}
