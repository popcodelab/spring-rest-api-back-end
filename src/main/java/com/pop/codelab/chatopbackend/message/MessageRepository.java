package com.pop.codelab.chatopbackend.message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Override
    List<Message> findAll();
}
