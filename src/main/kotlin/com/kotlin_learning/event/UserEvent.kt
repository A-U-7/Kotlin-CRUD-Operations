package com.kotlin_learning.event

data class UserEvent(
    val eventType: UserEventType,
    val userId: Long?,
    val username: String?,
    val email: String?
)

enum class UserEventType {
    USER_CREATED,
    USER_UPDATED,
    USER_DELETED
}
