package com.pop.codelab.chatopbackend.user;

import com.pop.codelab.chatopbackend.exception.ResourceNotFoundException;
import com.pop.codelab.chatopbackend.service.CrudService;
import com.pop.codelab.chatopbackend.user.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements CrudService<UserDto> {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> findAll() {
        return null;
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            throw new ResourceNotFoundException("No user found with Id : "+ id);
        }
        logger.debug("User Dto retrieved : {} ", userOptional);
        return userOptional.map(this::convertToDto);
    }

    @Override
    public UserDto save(UserDto dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public UserDto update(Long id, UserDto dto) {
        return null;
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
