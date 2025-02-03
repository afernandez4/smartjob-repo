package com.smartjob.users.mapper;

import com.smartjob.users.dto.UserRequestDto;
import com.smartjob.users.dto.UserResponseDto;
import com.smartjob.users.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = PhoneMapper.class)
public interface UserMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "phones", source = "phones")
  User toUser(UserRequestDto userRequestDto);

  @Mapping(target = "email", source = "email")
  UserResponseDto toUserResponseDto(User user);

  List<UserResponseDto> toUserResponseListDto(List<User> users);
}
