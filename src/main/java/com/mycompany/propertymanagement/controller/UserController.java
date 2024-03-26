package com.mycompany.propertymanagement.controller;

import com.mycompany.propertymanagement.dto.UserDTO;
import com.mycompany.propertymanagement.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> saveProperty(@Valid @RequestBody UserDTO userDTO){
        UserDTO savedUserDTO = userService.register(userDTO);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    @PostMapping("/login")

    public ResponseEntity<UserDTO> login(@RequestBody UserDTO userDTO){
        UserDTO savedUserDTO = userService.login(userDTO.getEmail(), userDTO.getPassword());
        return new ResponseEntity<>(savedUserDTO, HttpStatus.OK);
    }
}
