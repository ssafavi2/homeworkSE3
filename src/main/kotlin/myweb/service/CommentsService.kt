package myweb.service

import arrow.core.Either
import myweb.client.RestClient
import myweb.dto.CommentsRequest
import myweb.dto.weather.WeatherResponse
import myweb.model.*
import myweb.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service





interface CommentsService {
    fun findComments(userId:Long): Either<CommentsError,List<Comments>>
    fun saveComments(commentRequest: CommentsRequest): Comments
}

data class CommentsError(override val code: ErrorCode, override val message: String) : Error


@Service
class CommentsServiceImpl @Autowired constructor(val repository: CommentRepository, val restClient: RestClient): CommentsService {

    override fun findComments(userId: Long): Either<CommentsError, List<Comments>> {
        val commentsData = repository.findByInitialPostId(userId)
        return if (commentsData.isPresent) {
            Either.right(commentsData.get())
        } else
            Either.left(CommentsError(ErrorCode.UserInfoAlreadyExist, "No comment found"))
    }

    override fun saveComments(commentRequest: CommentsRequest): Comments {

        var weatherResponse = restClient.callWeatherApi(commentRequest.city)
        var comment = convertCommentRequestToComments(commentRequest,weatherResponse)
        return repository.save(comment)

    }

    private fun convertCommentRequestToComments(commentRequest: CommentsRequest, weatherResponse: WeatherResponse): Comments {

        return Comments(
            initialPostId = commentRequest.initialPostId,
            commentUserName = commentRequest.userName,
            comment = commentRequest.comment,
            city = commentRequest.city,
            latitude = weatherResponse.coord.lat,
            longitude = weatherResponse.coord.lon,
            temperature = weatherResponse.main.temp
        )
    }
}

