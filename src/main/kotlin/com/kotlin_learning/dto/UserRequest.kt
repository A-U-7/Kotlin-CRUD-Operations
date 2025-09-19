package com.kotlin_learning.dto

import java.time.LocalDateTime

data class UserRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int
)

data class UserResponse(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)