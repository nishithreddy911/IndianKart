package com.nishi.user.service;

import com.nishi.user.dto.AddressDTO;
import com.nishi.user.dto.UserDTO;
import com.nishi.user.entity.Address;
import com.nishi.user.entity.User;
import com.nishi.user.repository.AddressRepository;
import com.nishi.user.repository.UserRepository;
import com.nishi.user.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final AddressRepository addressRepo;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepo = userRepository;
        this.addressRepo = addressRepository;
    }


    public UserDTO createUser(UserDTO user) {
        User newUser = Utils.toUser(user);
        if(user.getAddress() != null) {
            AddressDTO address = user.getAddress();
            Address savedAddress = addressRepo.save(Utils.toAddress(address));
            newUser.setAddress(savedAddress);
            address = Utils.toAddressDTO(savedAddress);
        }
        newUser = userRepo.saveAndFlush(newUser);
        return Utils.toUserDTO(newUser);
    }
}
