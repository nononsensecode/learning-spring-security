package com.nononsensecode.secure.controller;

import com.nononsensecode.secure.application.service.impl.JwtUtilService;
import com.nononsensecode.secure.domain.dto.AuthenticationRequest;
import com.nononsensecode.secure.domain.dto.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtilService jwtUtilService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public HomeController(AuthenticationManager authenticationManager, JwtUtilService jwtUtilService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtilService = jwtUtilService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createJwtToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    request.getUsername(), request.getPassword()
            );
            this.authenticationManager.authenticate(authRequest);
        } catch (AuthenticationException exception) {
            throw new Exception("Username or password do not match");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = jwtUtilService.generateToken(userDetails);
        final AuthenticationResponse response = new AuthenticationResponse(jwtToken);
        return ResponseEntity.ok(response);
    }

    @RequestMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }
}
