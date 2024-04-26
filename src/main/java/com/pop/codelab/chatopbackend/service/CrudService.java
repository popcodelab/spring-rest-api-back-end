package com.pop.codelab.chatopbackend.service;

import com.pop.codelab.chatopbackend.controllers.dto.BaseDto;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface CrudService<T extends BaseDto> {

    List<T> findAll();

    Optional<T> findById(Long id) throws IOException;

    T save(T dto);

    void delete(Long id);

    T update(Long id, T dto);
}
