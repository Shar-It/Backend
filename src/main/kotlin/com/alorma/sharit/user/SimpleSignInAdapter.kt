package com.alorma.sharit.user;

import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

class SimpleSignInAdapter: SignInAdapter {

    val userCookieGenerator:UserCookieGenerator = UserCookieGenerator()

    override fun signIn(userId: String, connection: Connection<*>?, request: NativeWebRequest): String? {
        SecurityContext.user = User(userId);
        userCookieGenerator.addCookie(userId, request.getNativeResponse(HttpServletResponse::class.java));
        return null;
    }
}