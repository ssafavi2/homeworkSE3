package myweb.controller

import arrow.core.getOrHandle
import myweb.dto.CommentsRequest
import myweb.model.User
import myweb.service.UserService
import myweb.dto.UserRequest
import myweb.model.Comments
import myweb.model.ErrorCode
import myweb.service.CommentsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

data class ErrorResponse(val code: String, val message: String)
open class ServiceException(open val code: String, override val message: String) : Exception(message)
class UserNotFoundException(override val code: String, override val message: String) : ServiceException(code, message)
class UserEmptyException(override val code: String, override val message: String) : ServiceException(code, message)

@RestController
@RequestMapping
class UserController @Autowired constructor(val userService: UserService, val commentsService: CommentsService) {

    @GetMapping("/users/{user}")
    @ResponseBody
    fun returnAllSubmissionForTheGivenUser(@PathVariable("user") user: String): List<User> {
        var userInfo = userService.find(user).getOrHandle {
            when (it.code) {
                ErrorCode.UserInfoNotFound -> throw UserNotFoundException(it.code.name, it.message)
                ErrorCode.UserIsEmpty -> throw UserEmptyException(it.code.name, it.message)
                else -> throw Exception("Test")
            }

        }
        return userInfo
    }

    @GetMapping("/comments/{initialPostId}")
    @ResponseBody
    fun returnAllCommentsForAPost(@PathVariable("initialPostId") initialPostId: Long): List<Comments> {
        var comments = commentsService.findComments(initialPostId).getOrHandle {
            when (it.code) {
                ErrorCode.UserInfoNotFound -> throw UserNotFoundException(it.code.name, it.message)
                ErrorCode.UserIsEmpty -> throw UserEmptyException(it.code.name, it.message)
                else -> throw Exception("Test")
            }

        }
        return comments
    }

    @PostMapping("/users")
    fun saveUsers(@RequestBody request: UserRequest) : User {
        return userService.saveUserInfo(request)
    }

    @PostMapping("/comments")
    fun saveComments(@RequestBody request: CommentsRequest) : Comments {
        return commentsService.saveComments(request)
    }
}
