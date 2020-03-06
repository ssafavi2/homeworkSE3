package myweb.model


import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "comments")
data class Comments (

    @Id @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @Column(name = "initial_post_id", nullable = false)
    val initialPostId: Long,
    @Column(name = "comment_user_name", nullable = false)
    val commentUserName: String,
    @Column(name = "comment", nullable = false)
    val comment: String,
    @Column(name = "date_time", nullable = false)
    val dateTime: LocalDateTime = LocalDateTime.now(),
    @Column(name = "city", nullable = false)
    val city: String,
    @Column(name = "longitude", nullable = false)
    val longitude: String,
    @Column(name = "latitude", nullable = false)
    val latitude: String,
    @Column(name = "temperature", nullable = false)
    val temperature: String

)


