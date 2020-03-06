package myweb.dto


import myweb.model.Comments
import myweb.model.User
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.util.*
import javax.persistence.*



data class Posts (val user: User, val comments: List<Comments>)



