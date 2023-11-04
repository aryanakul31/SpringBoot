package com.nakul.user.security

import com.nakul.user.entities.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.security.Key
import java.util.*


object JwtUtil {
    private const val VALIDITY: Int = 3 * 24 * 60 * 60 * 1_000 //3Days
    private const val SECRET: String = "ededwedwwpolfePtbEadRzVlewewf2323ferferf2323"

    fun checkToken(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(signKey).build().parseClaimsJws(token).body
    }

    fun generateToken(userModel: User): String? {
        val tokenValidity = VALIDITY // = env["JWT.VALIDITY"]?.toInt() ?: 0
        val time = System.currentTimeMillis()
        return Jwts.builder()
            .claim("id", userModel.userId)
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