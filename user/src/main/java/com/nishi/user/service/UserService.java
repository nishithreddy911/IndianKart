package com.nishi.user.service;

import com.nishi.user.dto.AddressDTO;
import com.nishi.user.dto.UserDTO;
import com.nishi.user.entity.Address;
import com.nishi.user.entity.User;
import com.nishi.user.exception.InvalidRequestException;
import com.nishi.user.exception.ResourceNotFoundException;
import com.nishi.user.repository.AddressRepository;
import com.nishi.user.repository.UserRepository;
import com.nishi.user.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class UserService {

    private final UserRepository userRepo;
    private final AddressRepository addressRepo;

    @Autowired
    public UserService(UserRepository userRepository, AddressRepository addressRepository) {
        this.userRepo = userRepository;
        this.addressRepo = addressRepository;
    }

    public UserDTO getUser(long id) {
        Optional<User> user = userRepo.findById(id);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("No User Found");
        }
        AddressDTO addressDTO = Utils.toAddressDTO(user.get().getAddress());
        UserDTO userDTO = Utils.toUserDTO(user.get());
        userDTO.setAddress(addressDTO);
        return userDTO;
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO.getAddress() == null) {
            throw new IllegalArgumentException("Address is required");
        }
        Address savedAddress = addressRepo.save(Utils.toAddress(userDTO.getAddress()));
        User user = Utils.toUser(userDTO);
        user.setAddress(savedAddress);

        User savedUser = userRepo.save(user);
        UserDTO responseDTO = Utils.toUserDTO(savedUser);
        responseDTO.setAddress(Utils.toAddressDTO(savedAddress));
        return responseDTO;
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new InvalidRequestException("User ID is required for update");
        }
        if (userDTO.getAddress() == null || userDTO.getAddress().getId() == null) {
            throw new InvalidRequestException("Address is required for update");
        }

        User existingUser = userRepo.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with given details"));
        if (!Objects.equals(existingUser.getAddress().getId(), userDTO.getAddress().getId())) {
            throw new InvalidRequestException("Your request cannot be proceeded as the provided address details does not match with the existing records in database.");
        }

        Address updatedAddress = addressRepo.save(Utils.toAddress(userDTO.getAddress()));
        existingUser = Utils.toUser(userDTO);
        existingUser.setAddress(updatedAddress);
        User updatedUser = userRepo.save(existingUser);

        UserDTO responseDTO = Utils.toUserDTO(updatedUser);
        responseDTO.setAddress(Utils.toAddressDTO(updatedAddress));

        return responseDTO;
    }

    @Transactional
    public String deleteUser(Long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User not found with given details");
        }
        addressRepo.deleteById(user.get().getAddress().getId());
        userRepo.deleteById(userId);
        return "User deleted successfully";
    }
}
