package com.pop.codelab.chatopbackend.business.message.service;

import com.pop.codelab.chatopbackend.business.message.dto.requests.MessageToSendDto;
import com.pop.codelab.chatopbackend.common.responses.MessageDto;

/**
 * The MessageService interface provides a contract for creating messages.
 */
public interface MessageService {

    /**
     * Creates a new message using the provided MessageToSendDto.
     *
     * @param messageDto The MessageToSendDto object containing the message, user ID, and rental ID.
     * @return The created MessageDto object.
     */
    MessageDto create(MessageToSendDto messageDto);
}
