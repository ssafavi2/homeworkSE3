package com.loyaltyone.myweb.controller

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit4.SpringRunner

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(UserController::class)
@RunWith(SpringRunner::class)
class UserControllerTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun shoulReturnTheUserValue() {
        mockMvc.perform(get("/saman"))
                .andExpect(status().isOk())
                .andExpect(content().string("saman"))

    }
}

