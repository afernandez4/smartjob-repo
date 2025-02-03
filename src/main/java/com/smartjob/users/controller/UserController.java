package com.smartjob.users.controller;

import com.smartjob.users.dto.UserRequestDto;
import com.smartjob.users.dto.Response;
import com.smartjob.users.dto.UserResponseDto;
import com.smartjob.users.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService){
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<UserResponseDto> createUser(@RequestBody @Valid UserRequestDto userRequestDto) {
    return ResponseEntity.ok(userService.saveUser(userRequestDto));
  }

  @GetMapping
  public ResponseEntity<List<UserResponseDto>> getAllUsers() {
    return ResponseEntity.ok(userService.findAll());
  }

}
