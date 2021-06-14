package app.hdj.datepick.server

import org.springframework.web.bind.annotation.GetMapping
import app.hdj.data.*
import org.springframework.web.bind.annotation.RestController
import kotlin.random.Random

@RestController
class UserController {

    @GetMapping("users/")
    fun getUsers() = listOf(
        User(Random.nextLong(), "James"),
        User(Random.nextLong(), "Tom"),
        User(Random.nextLong(), "Peter"),
    )

}