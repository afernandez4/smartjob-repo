package com.smartjob.users.mapper;

import com.smartjob.users.dto.PhoneDto;
import com.smartjob.users.model.Phone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "user", ignore = true)
  Phone toPhone(PhoneDto phoneDto);

  PhoneDto toPhoneDto(Phone phone);
}
