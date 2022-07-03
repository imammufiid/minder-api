package com.mufiid.minderapi.authentication

import com.fasterxml.jackson.databind.ObjectMapper
import com.mufiid.minderapi.base.BaseResponse
import com.mufiid.minderapi.utils.Constant
import com.mufiid.minderapi.utils.Empty
import com.mufiid.minderapi.utils.MinderException
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.UnsupportedJwtException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            if (JwtConfig.isPermit(request)) {
                filterChain.doFilter(request, response)
            } else {
                val claims = validate(request)
                if (claims[Constant.CLAIMS] != null) {
                    setupAuthenticate(claims)
                    filterChain.doFilter(request, response)
                } else {
                    SecurityContextHolder.clearContext()
                    throw MinderException("Token required!")
                }
            }
        } catch (e: Exception) {
            val errorResponse = BaseResponse<Empty>()
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"

            when (e) {
                is UnsupportedJwtException -> {
                    errorResponse.message = "error unsupported!"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)

                    response.writer.println(responseString)
                }
                else -> {
                    errorResponse.message = "Token invalid!"
                    val responseString = ObjectMapper()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(errorResponse)
                    response.writer.println(responseString)
                }
            }
        }
    }

    private fun validate(request: HttpServletRequest): Claims {
        val jwtToken = request.getHeader("Authorization")
        return Jwts.parserBuilder()
            .setSigningKey(Constant.SECRET.toByteArray())
            .build()
            .parseClaimsJws(jwtToken)
            .body
    }

    private fun setupAuthenticate(claims: Claims) {
        val authorities = claims[Constant.CLAIMS] as List<String>
        val authStream = authorities.stream().map {
            SimpleGrantedAuthority(it)
        }.collect(Collectors.toList())
        val auth = UsernamePasswordAuthenticationToken(claims.subject, null, authStream)
        SecurityContextHolder.getContext().authentication = auth
    }
}