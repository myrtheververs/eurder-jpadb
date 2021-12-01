package com.switchfully.eurderjpadb.sevices;


import com.switchfully.eurderjpadb.api.dto.users.UserDTORequest;
import com.switchfully.eurderjpadb.api.dto.users.UserDTOResponse;
import com.switchfully.eurderjpadb.api.mappers.UserMapper;
import com.switchfully.eurderjpadb.domain.entities.User;
import com.switchfully.eurderjpadb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private boolean adminExists;

    @Autowired
    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDTOResponse createCustomer(UserDTORequest userDTORequest) {
        User created = userRepository.save(userMapper.toEntity(userDTORequest));
        return userMapper.toResponse(created);
    }

    public UserDTOResponse createAdmin(UserDTORequest adminToCreate) {
        if (adminExists) {
            throw new IllegalArgumentException("There can be only one admin");
        }
        User created = userRepository.save(userMapper.toAdminEntity(adminToCreate));
        adminExists = true;
        return userMapper.toResponse(created);
    }

    public List<UserDTOResponse> getAllUsers(Long adminId) {
        this.assertValidAdminId(adminId);
        return userRepository.findAll().stream()
                .filter(user -> user.getRole() == User.Role.CUSTOMER)
                .sorted(Comparator.comparing(User::getLastName).thenComparing(User::getFirstName))
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserDTOResponse getCustomerById(Long customerId, Long adminId) {
        assertValidAdminId(adminId);
        assertValidCustomerId(customerId);
        return userMapper.toResponse(userRepository.getById(customerId));
    }





    //HELPERS
    public void assertValidAdminId(Long id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Invalid admin id");
        }
        if (userRepository.findById(id).get().getRole() != User.Role.ADMIN) {
            throw new IllegalArgumentException("Invalid admin id");
        }
    }

    public void assertValidCustomerId(Long id) {
       assertUserId(id);
        if (userRepository.findById(id).get().getRole() != User.Role.CUSTOMER) {
            throw new IllegalArgumentException("Invalid customer id");
        }
    }

    public User findById(Long userId) {
        assertUserId(userId);
        return userRepository.getById(userId);
    }

    public void assertUserId(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new IllegalArgumentException("Invalid user id");
        }
    }

}
