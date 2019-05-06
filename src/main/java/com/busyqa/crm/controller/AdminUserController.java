package com.busyqa.crm.controller;

import com.busyqa.crm.message.response.ApiResponse;
import com.busyqa.crm.repo.UserRepository;
import com.busyqa.crm.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin/users")
public class AdminUserController {

    @Autowired
    UserRepository userRepository;


    @Autowired
    CrudService crudService;

    @GetMapping
    public ApiResponse<List<User>> listUser() {
        List<String> strTeams = new ArrayList<>();
        strTeams.add("TEAM_ADMIN");
        return new ApiResponse<>(HttpStatus.OK.value(), "User list fetched successfully.", crudService.getlist(strTeams));
    }
    @GetMapping("/{username}")
    public ApiResponse<User> getTeam(@PathVariable String username) {
        ApiResponse<User> userApiResponse = new ApiResponse<>(HttpStatus.BAD_REQUEST.value(), "Users Not found", null);
        Optional<com.busyqa.crm.model.User> user = userRepository.findByUsername(username);
        List<String> strTeams = user.get().getTeams();
        userApiResponse = new ApiResponse<>(HttpStatus.OK.value(), "User fetched successfully.", crudService.getlist(strTeams));

        return userApiResponse;

    }


}
