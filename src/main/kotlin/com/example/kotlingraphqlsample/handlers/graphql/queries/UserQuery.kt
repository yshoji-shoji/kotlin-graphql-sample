package com.example.kotlingraphqlsample.handlers.graphql.queries

import com.example.kotlingraphqlsample.handlers.graphql.types.UserType
import com.expediagroup.graphql.spring.operations.Query
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class UserQuery : Query {
    fun users(): List<UserType> {
        return listOf(UserType(1, "name", LocalDateTime.now(), LocalDateTime.now()))
    }
}
