package myweb.repository

import myweb.model.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface UserRepository : CrudRepository<User, Long> {

    fun findByUserName(userName: String): Optional<List<User>>


}


