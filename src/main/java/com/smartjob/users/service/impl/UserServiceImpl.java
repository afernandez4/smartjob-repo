package com.smartjob.users.service.impl;

import com.smartjob.users.dto.UserRequestDto;
import com.smartjob.users.dto.UserResponseDto;
import com.smartjob.users.exception.EntityExistsException;
import com.smartjob.users.mapper.UserMapper;
import com.smartjob.users.model.User;
import com.smartjob.users.repository.UserRepository;
import com.smartjob.users.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){
      this.userRepository = userRepository;
      this.userMapper = userMapper;
  }

  public UserResponseDto saveUser(UserRequestDto userRequestDto) {
    userRepository.findByEmail(userRequestDto.getEmail())
         .ifPresent(user -> {
          throw new EntityExistsException(userRequestDto.getEmail());
        });

    final var user = userMapper.toUser(userRequestDto);
    user.getPhones().forEach(phone -> phone.setUser(user));
    initUserData(user);

    return userMapper.toUserResponseDto(userRepository.save(user));
  }

    @Override
    public List<UserResponseDto> findAll() {
        return userMapper.toUserResponseListDto(userRepository.findAll());
    }

    private void initUserData(final User user){
      user.setId(UUID.randomUUID());
      user.setCreatedDate(new Date());
      user.setLastSessionDate(new Date());
      user.setActive(true);
  }
}
