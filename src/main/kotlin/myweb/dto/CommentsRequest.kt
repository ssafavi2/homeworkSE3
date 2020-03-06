package myweb.dto

import javax.validation.constraints.NotNull

data class CommentsRequest(

        @field:NotNull(message = "Initial Post cannot be null")
        val initialPostId: Long,
        @field:NotNull(message = "UserName cannot be null")
        val userName: String,
        @field:NotNull(message = "Comment cannot be null")
        val comment: String,
        @field:NotNull(message = "City cannot be null")
        val city: String

)
