package com.alorma.sharit.user;

/**
 * Simple SecurityContext that stores the currently signed-in connection in a thread local.
 * @author Keith Donald
 */


object SecurityContext {
    val currentUser: ThreadLocal<User> = ThreadLocal<User>()

    var user: User
        get() {
            val user: User? = currentUser.get();
            if (user == null) {
                throw IllegalStateException("No user is currently signed in");
            }
            return user;
        }
        set(value) {
            currentUser.set(value)
        }

    fun remove() {
        currentUser.remove()
    }

    fun userSignedIn(): Boolean{
        return currentUser.get() != null
    }
}