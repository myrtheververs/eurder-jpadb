package com.switchfully.eurderjpadb.api.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class UserDTORequest {

    @NotBlank(message = "First name can not be empty")
    @NotNull
    private final String firstName;

    @NotBlank(message = "Last name can not be empty")
    @NotNull
    private final String lastName;

    @NotBlank(message = "Email can not be empty")
    @Email(message = "Valid email needed")
    @NotNull
    private final String email;

    @NotBlank(message = "Address can not be empty")
    @NotNull
    private final String address;

    @NotBlank(message = "Phone can not be empty")
    @NotNull
    private final String phoneNumber;




}
