package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.message.response.UserResponse;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserRepository userRepository;


    @Autowired
    CrudService crudService;

    @GetMapping("/{username}")
    public ApiResponse<UserResponse> getUserInfo(@PathVariable String username) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", crudService.getByUsername(username));


    }
}
