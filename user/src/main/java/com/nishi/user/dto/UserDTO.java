package com.nishi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    public Long id;
    public String name;
    public String mobileNumber;
    public AddressDTO address;
}
