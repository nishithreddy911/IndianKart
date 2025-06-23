package com.nishi.user.dto;

import lombok.Data;

@Data
public class AddressDTO {

    public Long id;
    public String street;
    public String city;
    public String state;
    public String zip;
    public String country;
    public String phone;
}
