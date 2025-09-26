package com.kotlin_learning.service


import com.kotlin_learning.dto.UserRequest
import com.kotlin_learning.dto.UserResponse
import com.kotlin_learning.model.User
import com.kotlin_learning.repository.UserRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.cache.annotation.Caching
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
open class UserService(private val userRepository: UserRepository) {

    @Cacheable(value = ["users"], key = "'all'")
    open fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }

    @Cacheable(value = ["user"], key = "#id")
    open fun getUserById(id: Long): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { RuntimeException("User not found with id: $id") }
        return user.toResponse()
    }
    @Transactional
    @Caching(
        evict = [
            CacheEvict(value = ["users"], key = "'all'")
        ]
    )
    open fun createUser(request: UserRequest): UserResponse {
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

    @Transactional
    @Caching(
        put = [CachePut(value = ["user"], key = "#id")],
        evict = [CacheEvict(value = ["users"], key = "'all'")]
    )
    open fun updateUser(id: Long, request: UserRequest): UserResponse {
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

    @Transactional
    @Caching(
        evict = [
            CacheEvict(value = ["user"], key = "#id"),
            CacheEvict(value = ["users"], key = "'all'")
        ]
    )
    open fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw RuntimeException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }
    // Clear all caches
    @Caching(
        evict = [
            CacheEvict(value = ["users"], allEntries = true),
            CacheEvict(value = ["user"], allEntries = true)
        ]
    )
    open fun clearAllCaches() {
        // This method will clear all caches when called

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