package com.alorma.sharit.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.util.CookieGenerator;

/**
 * Utility class for managing the quick_start user cookie that remembers the signed-in user.
 * @author Keith Donald
 */


class UserCookieGenerator() {
    val userCookieGenerator: CookieGenerator = CookieGenerator()

    init {
        userCookieGenerator.cookieName = "sharit_user"
    }

    fun addCookie(userId: String, response: HttpServletResponse) {
        userCookieGenerator.addCookie(response, userId)
    }

    fun removeCookie(response: HttpServletResponse) {
        userCookieGenerator.addCookie(response, "")
    }

    fun readCookieValue(request: HttpServletRequest): String? {
        val cookies = request.getCookies() ?: null

        cookies?.forEach {
            if (it.name == userCookieGenerator.cookieName) {
                return it.value;
            }

        }
        return null
    }
}