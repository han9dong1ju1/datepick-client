package app.hdj.datepick.server.service

import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Service

@Service
class JsonWebTokenService {

    fun generateJwt(userId: Long): String {
        return Jwts.builder()
            .compact()
    }

}