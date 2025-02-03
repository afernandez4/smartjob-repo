package com.smartjob.users.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PhoneDto {
  private String number;
  private String cityCode;
  private String countryCode;

}
