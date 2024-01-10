package com.teamsparta.reviewers.domain.authorization

import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import java.util.*
import java.util.concurrent.ConcurrentHashMap


@Component
public class SessionManager {

    private val sessionStore: MutableMap<String, Any> = ConcurrentHashMap()

    fun createSession(value:Any, response: HttpServletResponse) {
        val sessionId = UUID.randomUUID().toString()
        sessionStore[sessionId] = value

        val mySessionCookie = Cookie(SESSION_COOKIE_NAME, sessionId)
        response.addCookie(mySessionCookie)
    }

    fun getSession(request: HttpServletRequest): Any? {
        val sessionCookie = findCookie(request, SESSION_COOKIE_NAME)
        return if (sessionCookie == null) null else sessionStore[sessionCookie.value]
    }

    fun expire(request: HttpServletRequest) {
        val sessionCookie = findCookie(request, SESSION_COOKIE_NAME)
        if (sessionCookie != null) {
            sessionStore.remove(sessionCookie.value)
        }
    }

    fun findCookie(request: HttpServletRequest, cookieName:String):Cookie? {
        val cookies = request.cookies ?: return null
        return cookies.firstOrNull { it.name == cookieName }
    }

    companion object {
        const val SESSION_COOKIE_NAME = "mySessionId"
    }

}