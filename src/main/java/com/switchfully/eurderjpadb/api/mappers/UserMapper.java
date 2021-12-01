package com.switchfully.eurderjpadb.api.mappers;


import com.switchfully.eurderjpadb.api.dto.users.UserDTORequest;
import com.switchfully.eurderjpadb.api.dto.users.UserDTOResponse;
import com.switchfully.eurderjpadb.domain.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDTOResponse toResponse(User user) {
        return UserDTOResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phoneNumber(user.getPhoneNumber())
                .role(user.getRole())
                .build();
    }

    public User toEntity(UserDTORequest userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(User.Role.CUSTOMER)
                .build();
    }

    public User toAdminEntity(UserDTORequest userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(User.Role.ADMIN)
                .build();
    }
}
