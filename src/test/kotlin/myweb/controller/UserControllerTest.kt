package myweb.controller

import com.fasterxml.jackson.databind.ObjectMapper
import myweb.dto.UserRequest
import myweb.model.User
import myweb.service.CommentsService
import myweb.service.UserService
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.json.JacksonTester
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime


@WebMvcTest(UserController::class)
@RunWith(SpringRunner::class)
class UserControllerTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockBean
    private lateinit var commentsService: CommentsService

    @MockBean
    private lateinit var userService: UserService

    private lateinit var jsonResponse: JacksonTester<User>

    private lateinit var jsonRequest: JacksonTester<UserRequest>

    @Before
    fun setUp() {
        JacksonTester.initFields(this, objectMapper)
    }

    @Test
    fun shoulReturnTheUserValue() {

        var userRequest = UserRequest("Saman","Saman","Saman")
        var userRequestJson = jsonRequest.write(userRequest).json
        var user = User(0,"Saman","Saman", LocalDateTime.now(),"Toronto","2.2","2.2","4")
        `when`(userService.saveUserInfo(userRequest)).thenReturn(user)

        mockMvc
            .perform(post("/users")
                .content(userRequestJson)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)


    }
}

