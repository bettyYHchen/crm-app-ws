package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.message.response.UserResponse;
import com.busyqa.crm.model.User;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;




@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/pm/users")
public class ManagerUserController {
    @Autowired
    UserRepository userRepository;


    @Autowired
    CrudService crudService;

    @GetMapping("/{username}")
    public ApiResponse<UserResponse> getUsersOnly(@PathVariable String username) {

        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new RuntimeException("Fail! -> Cause: User not find."));


        ApiResponse<UserResponse> userApiResponse = new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", crudService.getUsers());

        return userApiResponse;

    }
}
