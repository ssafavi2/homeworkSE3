package myweb.service

import myweb.model.User
import arrow.core.Either
import myweb.client.RestClient
import myweb.dto.Posts
import myweb.repository.UserRepository
import myweb.dto.UserRequest
import myweb.dto.weather.WeatherResponse
import myweb.model.Error
import myweb.model.ErrorCode
import myweb.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service





interface UserService {
    fun find(user:String): Either<UserError,List<User>>
    fun saveUserInfo(userRequest: UserRequest): User
}

data class UserError(override val code: ErrorCode, override val message: String) : Error


@Service
class UserServiceImpl @Autowired constructor(val repository: UserRepository,val commentsRepository: CommentRepository, val restClient: RestClient): UserService {

    override fun find(user: String): Either<UserError, List<User>> {
        val userData = repository.findByUserName(user)

        if (user.isNullOrBlank()) {
            Either.left(UserError(ErrorCode.UserIsEmpty, "User is empty, please enter a valid user"))
        }
        return if (userData.isPresent) {
            Either.right(userData.get())
        } else
            Either.left(UserError(ErrorCode.UserInfoNotFound, "No user data is found"))
    }

    override fun saveUserInfo(userRequest: UserRequest): User {

        var weatherResponse = restClient.callWeatherApi(userRequest.city)
        var user = convertUserRequestToUser(userRequest,weatherResponse)
        return repository.save(user)

    }

    private fun convertUserRequestToUser(userRequest: UserRequest, weatherResponse: WeatherResponse): User {

        return User(
            userName = userRequest.userName,
            metaData = userRequest.metaData,
            city = userRequest.city,
            latitude = weatherResponse.coord.lat,
            longitude = weatherResponse.coord.lon,
            temperature = weatherResponse.main.temp
        )
    }
}

