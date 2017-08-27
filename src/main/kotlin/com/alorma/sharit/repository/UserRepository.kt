package com.alorma.sharit.repository;

import com.alorma.sharit.domain.AppUser;
import cz.jirutka.spring.data.jdbc.BaseJdbcRepository
import cz.jirutka.spring.data.jdbc.RowUnmapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository;

@Repository
class UserRepository : BaseJdbcRepository<AppUser, String>(mapper(), unmapper(), "\"users\"", "login") {
    fun findById(query: String) : List<AppUser> {
        return findAll().filter { it.login.contains(query)}
    }
    fun findByName(query: String) : List<AppUser> {
        return findAll().filter { it.name.contains(query)}
    }
    fun findByEmail(query: String) : List<AppUser> {
        return findAll().filter { it.email.contains(query)}
    }
}

private fun mapper() = RowMapper<AppUser> {
    rs, _ ->
    AppUser(
            rs.getString("login"),
            rs.getString("user_name"),
            rs.getString("token"),
            rs.getString("email"))
}

private fun unmapper() = RowUnmapper<AppUser> { user ->
    val map = mutableMapOf<String, Any>(
            Pair("login", user.login),
            Pair("user_name", user.name),
            Pair("token", user.token),
            Pair("email", user.email))
    map;
}