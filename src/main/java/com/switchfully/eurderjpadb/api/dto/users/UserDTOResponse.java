package com.switchfully.eurderjpadb.api.dto.users;

import com.switchfully.eurderjpadb.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserDTOResponse {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String phoneNumber;
    private final User.Role role;


}
