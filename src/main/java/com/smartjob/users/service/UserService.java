package com.smartjob.users.service;

import com.smartjob.users.dto.UserRequestDto;
import com.smartjob.users.dto.UserResponseDto;

import java.util.List;

public interface UserService {

  UserResponseDto saveUser(UserRequestDto userRequestDto);

  List<UserResponseDto> findAll();

}
