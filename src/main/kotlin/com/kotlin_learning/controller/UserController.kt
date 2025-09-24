package com.kotlin_learning.controller

import com.kotlin_learning.dto.ApiResponse
import com.kotlin_learning.dto.UserRequest
import com.kotlin_learning.event.UserEvent
import com.kotlin_learning.event.UserEventType
import com.kotlin_learning.service.UserService
import com.kotlin_learning.service.UserEventProducer
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userService: UserService,
    private val userEventProducer: UserEventProducer
) {

    // http://localhost:8080/api/users
    @GetMapping
    fun getAllUsers(): ResponseEntity<ApiResponse<List<*>>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(
            ApiResponse(success = true, message = "Users retrieved successfully", data = users)
        )
    }

    // http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<ApiResponse<*>> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(
            ApiResponse(success = true, message = "User retrieved successfully", data = user)
        )
    }

    // http://localhost:8080/api/users
    @PostMapping
    fun createUser(@Valid @RequestBody request: UserRequest): ResponseEntity<ApiResponse<*>> {
        val user = userService.createUser(request)
        
        // Publish user created event
        userEventProducer.sendUserEvent(
            UserEvent(
                eventType = UserEventType.USER_CREATED,
                userId = user.id,
                username = user.firstName,
                email = user.email
            )
        )
        
        return ResponseEntity.status(HttpStatus.CREATED).body(
            ApiResponse(success = true, message = "User created successfully", data = user)
        )
    }

    // http://localhost:8080/api/users/1
    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody request: UserRequest): ResponseEntity<ApiResponse<*>> {
        val user = userService.updateUser(id, request)
        
        // Publish user updated event
        userEventProducer.sendUserEvent(
            UserEvent(
                eventType = UserEventType.USER_UPDATED,
                userId = user.id,
                username = user.firstName,
                email = user.email
            )
        )
        
        return ResponseEntity.ok(
            ApiResponse(success = true, message = "User updated successfully", data = user)
        )
    }

    // http://localhost:8080/api/users/1
    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<ApiResponse<Unit>> {
        // Get user before deletion to include in the event
        val user = userService.getUserById(id)
        
        userService.deleteUser(id)
        
        // Publish user deleted event
        userEventProducer.sendUserEvent(
            UserEvent(
                eventType = UserEventType.USER_DELETED,
                userId = user.id,
                username = user.firstName,
                email = user.email
            )
        )
        
        return ResponseEntity.ok(
            ApiResponse(success = true, message = "User deleted successfully")
        )
    }
}