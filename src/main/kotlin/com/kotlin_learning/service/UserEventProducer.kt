package com.kotlin_learning.service

import com.kotlin_learning.event.UserEvent
import com.kotlin_learning.event.UserEventType
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

@Service
class UserEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    
    private val logger = LoggerFactory.getLogger(UserEventProducer::class.java)
    
    fun sendUserEvent(userEvent: UserEvent) {
        val future: CompletableFuture<SendResult<String, Any>> = 
            kafkaTemplate.send("user-events", userEvent.userId?.toString() ?: "unknown", userEvent)
        
        future.whenComplete { result, ex ->
            if (ex == null) {
                logger.info("Sent message=[${userEvent}] with offset=[${result.recordMetadata.offset()}]")
            } else {
                logger.error("Unable to send message=[${userEvent}] due to: ${ex.message}")
            }
        }
    }
}
