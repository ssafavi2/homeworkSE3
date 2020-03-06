package myweb.repository

import myweb.model.Comments
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface CommentRepository : CrudRepository<Comments, Long> {

    fun findByInitialPostId(userId: Long): Optional<List<Comments>>


}


