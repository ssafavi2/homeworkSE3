package com.loyaltyone.myweb.controller

import com.loyaltyone.myweb.model.User
import com.loyaltyone.myweb.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping
class UserController @Autowired constructor(val userRepository: UserRepository) {


    @GetMapping("/{user}")
    @ResponseBody
    fun returnAllSubmissionForTheGivenUser(@PathVariable("user") user: String): Optional<List<User>>
    {
        return userRepository.findByUserName(user)
    }


    @PostMapping("/users")
    fun saveUsers(@RequestBody request: User):User {
        return userRepository.save(request)
    }
}
