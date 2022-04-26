package com.challenge.library.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/r")
public record UserController(UserService userService) {
    @PostMapping
    public void registerUser(@RequestBody UserRequest userRequest){
        log.info("New user registration {}", userRequest);
        userService.registerUser(userRequest);
    }

}
