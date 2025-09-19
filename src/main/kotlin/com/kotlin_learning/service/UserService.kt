package com.kotlin_learning.service


import com.kotlin_learning.dto.UserRequest
import com.kotlin_learning.dto.UserResponse
import com.kotlin_learning.model.User
import com.kotlin_learning.repository.UserRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class UserService(private val userRepository: UserRepository) {

    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }

    fun getUserById(id: Long): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { RuntimeException("User not found with id: $id") }
        return user.toResponse()
    }

    fun createUser(request: UserRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists: ${request.email}")
        }
        
        val user = User(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            age = request.age
        )
        
        val savedUser = userRepository.save(user)
        return savedUser.toResponse()
    }

    fun updateUser(id: Long, request: UserRequest): UserResponse {
        val existingUser = userRepository.findById(id)
            .orElseThrow { RuntimeException("User not found with id: $id") }
        
        if (request.email != existingUser.email && userRepository.existsByEmail(request.email)) {
            throw RuntimeException("Email already exists: ${request.email}")
        }
        
        val updatedUser = existingUser.copy(
            firstName = request.firstName,
            lastName = request.lastName,
            email = request.email,
            age = request.age,
            updatedAt = LocalDateTime.now()
        )
        
        val savedUser = userRepository.save(updatedUser)
        return savedUser.toResponse()
    }

    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw RuntimeException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }

    private fun User.toResponse(): UserResponse {
        return UserResponse(
            id = id!!,
            firstName = firstName,
            lastName = lastName,
            email = email,
            age = age,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}