package com.smartjob.users.service;

import com.smartjob.users.dto.PhoneDto;
import com.smartjob.users.dto.UserRequestDto;
import com.smartjob.users.dto.UserResponseDto;
import com.smartjob.users.exception.EntityExistsException;
import com.smartjob.users.mapper.UserMapper;
import com.smartjob.users.model.User;
import com.smartjob.users.repository.UserRepository;
import com.smartjob.users.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveUser_shouldSaveUser_whenEmailDoesNotExist() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("User1")
                .email("test@email.com")
                .password("passwordA1")
                .phones(List.of(PhoneDto.builder()
                        .number("123456")
                        .build()))
                .build();
        User user = new User();
        user.setPhones(new ArrayList<>());

        UserResponseDto expectedResponse = UserResponseDto.builder()
                .id(UUID.randomUUID())
                .email("test@email.com")
                .createdDate(new Date())
                .lastSessionDate(new Date())
                .build();

        when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(Optional.empty());
        when(userMapper.toUser(userRequestDto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toUserResponseDto(user)).thenReturn(expectedResponse);

        UserResponseDto result = userService.saveUser(userRequestDto);

        assertNotNull(result);
        assertEquals(expectedResponse, result);
        verify(userRepository).save(user);
    }

    @Test
    void saveUser_shouldThrowException_whenEmailAlreadyExists() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name("User1")
                .email("test@email.com")
                .password("passwordA1")
                .phones(List.of(PhoneDto.builder()
                        .number("123456")
                        .build()))
                .build();
        User existingUser = new User();
        existingUser.setPhones(new ArrayList<>());

        when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(Optional.of(existingUser));

        assertThrows(EntityExistsException.class, () -> userService.saveUser(userRequestDto));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void findAll_shouldReturnListOfUsers() {
        List<User> users = List.of(new User(), new User());
        List<UserResponseDto> expectedResponse = List.of(
                UserResponseDto.builder().build(),
                UserResponseDto.builder().build());

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toUserResponseListDto(users)).thenReturn(expectedResponse);

        List<UserResponseDto> result = userService.findAll();

        assertEquals(2, result.size());
        verify(userRepository).findAll();
    }

}
