package com.smartjob.users.dto;

import com.smartjob.users.validator.ValidPassword;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
public class UserRequestDto {
  private String name;
  @Pattern( regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",
          message = "Email not valid")
  private String email;
  @ValidPassword
  private String password;
  private List<PhoneDto> phones;

  public String getName() {
    return name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public List<PhoneDto> getPhones() {
    return phones;
  }

}
