package com.switchfully.eurderjpadb.api;


import com.switchfully.eurderjpadb.api.dto.users.UserDTORequest;
import com.switchfully.eurderjpadb.api.dto.users.UserDTOResponse;
import com.switchfully.eurderjpadb.sevices.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/admins")
public class AdminController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTOResponse createAdmin(@Valid @RequestBody UserDTORequest userDTORequest) {
        logger.info("Creating new admin for email: " + userDTORequest.getEmail());
        return userService.createAdmin(userDTORequest);
    }
}
