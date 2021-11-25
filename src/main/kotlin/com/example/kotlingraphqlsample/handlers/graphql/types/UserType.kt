package com.example.kotlingraphqlsample.handlers.graphql.types

import java.time.LocalDateTime

data class UserType(val id: Int, val name: String, val createdAt: LocalDateTime, val updatedAt: LocalDateTime)
