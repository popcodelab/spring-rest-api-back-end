package com.pop.codelab.chatopbackend.auth;

import com.pop.codelab.chatopbackend.user.dto.UserCreationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST})
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserCreationDto userDto
    ) {
        return ResponseEntity.ok(authenticationService.register(userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

  @GetMapping("/me")
  public String getUserDetails() {
    // Retrieve authentication object from security context
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    // Get principal (authenticated user)
    String username = authentication.getName(); // Retrieve username

    // You can also retrieve user's authorities/roles if needed
    // List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();

    return "Authenticated User: " + username;
  }


}
