package com.nakul.user.secruity

import com.nakul.user.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.*


object JwtUtil {
    private var VALIDITY: Int = 3 * 24 * 60 * 60 * 1_000 //3Days
    private var SECRET: String = "ededwedwwpolfePtbEadRzVlewewf2323ferferf2323"

    fun checkToken(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).body
    }

    fun generateToken(userModel: User): String? {
        val tokenValidity = VALIDITY // = env["JWT.VALIDITY"]?.toInt() ?: 0
        val time = System.currentTimeMillis()
        return Jwts.builder()
            .claim("id", userModel.id)
            .setIssuedAt(Date(time))
            .setExpiration(Date(time + tokenValidity))
            .signWith(signKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private val signKey: Key
        get() {
            val keyBytes = Decoders.BASE64.decode(SECRET)
            return Keys.hmacShaKeyFor(keyBytes)
        }
}