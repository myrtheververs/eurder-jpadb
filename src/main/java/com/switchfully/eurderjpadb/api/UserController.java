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
import java.util.List;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //create customer
    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTOResponse createCustomer(@Valid @RequestBody UserDTORequest userDTORequest) {
        logger.info("Creating new user for email: " + userDTORequest.getEmail());
        return userService.createCustomer(userDTORequest);
    }

    //get customers
    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTOResponse> getAllUsers(@RequestHeader(value = "id") Long adminId) {
        logger.info("Admin getting all users");
        return userService.getAllUsers(adminId);
    }

    //GET
    @GetMapping(path = "{customerId}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public UserDTOResponse getCusyomerById(@PathVariable Long customerId,
                                       @RequestHeader(value = "id") Long adminId) {
        logger.info("Admin getting user with id " + customerId);
        return userService.getCustomerById(customerId, adminId);
    }

}
