package com.loyaltyone.myweb.service

import com.loyaltyone.myweb.model.User
import arrow.core.Either
import com.loyaltyone.myweb.repository.UserRepository
import myweb.model.Error
import myweb.model.UserErrorCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service





interface UserService {
    fun find(user:String): Either<UserError,List<User>>
    fun saveUserInfo(user:User): User
}

data class UserError(override val code: UserErrorCode, override val message: String) : Error


@Service
class UserServiceImpl @Autowired constructor(val repository:UserRepository): UserService {

    override fun find(user: String): Either<UserError, List<User>> {
        val userData = repository.findByUserName(user)

        if (user.isNullOrBlank()) {
            Either.left(UserError(UserErrorCode.UserIsEmpty,"User is empty, please enter a valid user"))
        }
        return if (userData.isPresent) {
            Either.right(userData.get())
        } else
            Either.left(UserError(UserErrorCode.UserInfoNotFound,"No user data is found"))
    }

    override fun saveUserInfo(user: User) : User {

        return repository.save(user)


    }

}

