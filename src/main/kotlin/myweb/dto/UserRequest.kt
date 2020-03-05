package myweb.dto

import javax.validation.constraints.NotNull

data class UserRequest(

        @field:NotNull(message = "UserName cannot be null")
        val userName: String,
        @field:NotNull(message = "Info cannot be null")
        val metaData: String,
        @field:NotNull(message = "City cannot be null")
        val city: String

)
