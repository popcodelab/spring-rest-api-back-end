package com.pop.codelab.chatopbackend.business.message.repository;

import com.pop.codelab.chatopbackend.business.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The MessageRepository interface extends the JpaRepository interface to provide CRUD operations for Message entities.
 * It represents a repository for managing and accessing Message objects in the system.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
}
