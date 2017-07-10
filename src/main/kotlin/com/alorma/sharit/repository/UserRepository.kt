package com.alorma.sharit.repository;

import com.alorma.sharit.domain.AppUser;
import cz.jirutka.spring.data.jdbc.BaseJdbcRepository
import cz.jirutka.spring.data.jdbc.RowUnmapper
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository;

@Repository
open class UserRepository : BaseJdbcRepository<AppUser, String>(mapper(), unmapper(), "\"users\"", "login")

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