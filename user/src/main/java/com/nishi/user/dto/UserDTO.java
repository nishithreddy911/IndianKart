package com.nishi.user.dto;

import lombok.Data;

@Data
public class UserDTO {

    public Long id;
    public String name;
    public String mobileNumber;
    public AddressDTO address;
}
