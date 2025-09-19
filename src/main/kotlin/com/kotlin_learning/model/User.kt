package com.kotlin_learning.model

import jakarta.persistence.*
import jakarta.validation.constraints.*
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @field:NotBlank(message = "First name is required")
    @field:Size(min = 2, max = 50, message = "First name must be between {min} and {max} characters")
    @field:Pattern(regexp = "^[a-zA-Z\\s'-]+", message = "First name can only contain letters, spaces, hyphens, and apostrophes")
    @Column(nullable = false, length = 50)
    val firstName: String,
    
    @field:NotBlank(message = "Last name is required")
    @field:Size(min = 2, max = 50, message = "Last name must be between {min} and {max} characters")
    @field:Pattern(regexp = "^[a-zA-Z\\s'-]+", message = "Last name can only contain letters, spaces, hyphens, and apostrophes")
    @Column(nullable = false, length = 50)
    val lastName: String,
    
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Invalid email format")
    @field:Size(min = 5, max = 100, message = "Email must be between {min} and {max} characters")
    @Column(nullable = false, unique = true, length = 100)
    val email: String,
    
    @field:NotNull(message = "Age is required")
    @field:Min(value = 1, message = "Age must be at least {value}")
    @field:Max(value = 150, message = "Age cannot be more than {value}")
    @Column(nullable = false)
    val age: Int,
    
    val createdAt: LocalDateTime = LocalDateTime.now(),
    
    val updatedAt: LocalDateTime = LocalDateTime.now()
)