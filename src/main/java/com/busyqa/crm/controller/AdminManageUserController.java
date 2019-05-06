package com.busyqa.crm.controller;

import com.busyqa.crm.message.request.SignUpForm;
import com.busyqa.crm.message.request.UserRequest;
import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.message.response.UserResponse;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.services.CrudService;
import com.busyqa.crm.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/user")
public class AdminManageUserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    @Autowired
    CrudService crudService;

    @GetMapping("/{username}")
    public ApiResponse<UserResponse> getOne(@PathVariable String username){
        return new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.",crudService.getByUsername(username));
    }

    // change other users' info
    @PutMapping("/{username}")
    public ApiResponse<UserResponse> update(@PathVariable String username,@RequestBody UserRequest userRequest) {
        return new ApiResponse<>(HttpStatus.OK.value(), "User updated successfully.",crudService.update(username,userRequest));
    }





    @DeleteMapping("/{username}")
    public ApiResponse<Void> delete(@PathVariable String username) {
        crudService.delete(username);
        return new ApiResponse<>(HttpStatus.OK.value(), "User deleted successfully.", null);
    }


}
