package myweb.model


import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "users")
data class User (

    @Id @Column(name = "id", nullable = false )
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
    @Column(name = "username", nullable = false)
    val userName: String,
    @Column(name = "metadata", nullable = false)
    val metaData: String,
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



