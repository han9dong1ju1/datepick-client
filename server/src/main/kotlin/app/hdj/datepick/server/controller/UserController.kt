package app.hdj.datepick.server.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class UserController {

    @GetMapping("users/")
    fun getUsers() = listOf<String>(
    )

}