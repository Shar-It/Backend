package com.alorma.sharit.user;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 * Simple little {@link ConnectionSignUp} command that allocates new userIds in memory.
 * Doesn't bother storing a user record in any local database, since this quickstart just stores the user id in a cookie.
 * @author Keith Donald
 */

class SimpleConnectionSignUp:ConnectionSignUp {

    val userIdSequence: AtomicLong = AtomicLong()

    override fun execute(connection: Connection<*>?): String {
        return userIdSequence.incrementAndGet().toString();
    }
}