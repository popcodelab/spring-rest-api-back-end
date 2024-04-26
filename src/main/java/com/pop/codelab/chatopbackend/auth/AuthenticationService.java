package com.pop.codelab.chatopbackend.auth;

import com.pop.codelab.chatopbackend.security.jwt.JwtService;
import com.pop.codelab.chatopbackend.user.User;
import com.pop.codelab.chatopbackend.user.UserRepository;
import com.pop.codelab.chatopbackend.user.dto.UserCreationDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import static com.pop.codelab.chatopbackend.user.UserMapper.INSTANCE;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  @Autowired
  private ModelMapper modelMapper;

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

  public AuthenticationResponse register(UserCreationDto userCreationDto) {
    logger.debug("Register user : {}", userCreationDto);
//    var user = User.builder()
//            .name(userCreationDto.getName())
//        .email(userCreationDto.getEmail())
//        .password(passwordEncoder.encode(userCreationDto.getPassword()))
//        .role((userCreationDto.getRole()==null? Role.USER:userCreationDto.getRole()))
//        .build();
    //User user = INSTANCE.dtoToUser(userCreationDto);

    User user  = modelMapper.map(userCreationDto, User.class);
    user.setPassword(passwordEncoder.encode(userCreationDto.getPassword()));
    userRepository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    var authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    SecurityContextHolder.getContext().setAuthentication(authentication);
    //var principal = (UserPrincipal) authentication.getPrincipal();

    var user = userRepository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
        .build();
  }

}
