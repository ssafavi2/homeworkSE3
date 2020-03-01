package com.loyaltyone.myweb.controller

import arrow.core.getOrHandle
import com.loyaltyone.myweb.model.User
import com.loyaltyone.myweb.service.UserService
import myweb.model.UserErrorCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

data class ErrorResponse(val code: String, val message: String)
open class ServiceException(open val code: String, override val message: String) : Exception(message)
class UserNotFoundException(override val code: String, override val message: String) : ServiceException(code, message)
class UserEmptyException(override val code: String, override val message: String) : ServiceException(code, message)

@RestController
@RequestMapping
class UserController @Autowired constructor(val userService: UserService) {


    @GetMapping("/{user}")
    @ResponseBody
    fun returnAllSubmissionForTheGivenUser(@PathVariable("user") user: String): List<User> {
        var userInfo = userService.find(user).getOrHandle {
            when (it.code) {
                UserErrorCode.UserInfoNotFound -> throw UserNotFoundException(it.code.name, it.message)
                UserErrorCode.UserIsEmpty -> throw UserEmptyException(it.code.name, it.message)
                else -> throw Exception("Test")
            }

        }
        return userInfo
    }


    @PostMapping("/users")
    fun saveUsers(@RequestBody request: User) : User {
        return userService.saveUserInfo(request)
    }
}
