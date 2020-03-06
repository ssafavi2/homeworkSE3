package myweb.service

import myweb.client.RestClient
import myweb.dto.UserRequest
import myweb.dto.weather.Coordination
import myweb.dto.weather.Main
import myweb.dto.weather.WeatherResponse
import myweb.model.User
import myweb.repository.CommentRepository
import myweb.repository.UserRepository
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import java.time.LocalDateTime
import java.util.*

class UserServiceTests {

    private lateinit var service: UserService
    private lateinit var restClient: RestClient
    private lateinit var commentRepository: CommentRepository
    private lateinit var userRepository: UserRepository


    @Before
    fun setUp() {
        restClient = mock(RestClient::class.java)
        commentRepository = mock(CommentRepository::class.java)
        userRepository = mock(UserRepository::class.java)
        service = UserServiceImpl(userRepository,commentRepository,restClient)

    }

    @Test
    fun findUserInformationSuccessfully() {

        var user = User(1,"Saman","Saman", LocalDateTime.now(),"Toronto","2.2","2.2","4")
        `when`(userRepository.findByUserName("Saman")).thenReturn(Optional.of(listOf(user)))
        val result = service.find("Saman")
        verify(userRepository,times(1)).findByUserName("Saman")


    }


    @Test
    fun saveUserInformationSuccessfully() {

        var userRequest = UserRequest("Saman","Saman","Toronto")
        var user = User(0,userRequest.userName,userRequest.metaData, LocalDateTime.now(),userRequest.city,"2.2","2.2","4")
        var coord = Coordination("2.2","3.3")
        var main = Main("2","2","2","2","2")
        var weatherResponse = WeatherResponse(coord,main)
        `when`(userRepository.save(ArgumentMatchers.any(User::class.java))).thenReturn(user)
        `when`(restClient.callWeatherApi(userRequest.city)).thenReturn(weatherResponse)
         service.saveUserInfo(userRequest)
        verify(userRepository,times(1)).save(any(User::class.java))


    }


}