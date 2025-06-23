package com.nishi.user.utils;

import com.nishi.user.dto.AddressDTO;
import com.nishi.user.dto.UserDTO;
import com.nishi.user.entity.Address;
import com.nishi.user.entity.User;
import org.springframework.beans.BeanUtils;

public class Utils {

    public static UserDTO toUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        if (user.getAddress() != null) {
            userDTO.setAddress(toAddressDTO(user.getAddress()));
        }
        return userDTO;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

    public static AddressDTO toAddressDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(address, addressDTO);
        return addressDTO;
    }

    public static Address toAddress(AddressDTO addressDTO) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDTO, address);
        return address;
    }
}
