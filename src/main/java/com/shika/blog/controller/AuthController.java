package com.shika.blog.controller;

import com.shika.blog.dto.LoginRequest;
import com.shika.blog.dto.RegisterRequest;
import com.shika.blog.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private AuthService authService;

    @Autowired
    public AuthController(RegisterRequest registerRequest,
                          AuthService authService,
                          LoginRequest loginRequest) {
        this.registerRequest = registerRequest;
        this.authService = authService;
        this.loginRequest = loginRequest;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signUp(@RequestBody RegisterRequest registerRequest) {
        return authService.signUp(registerRequest);
//        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

}
