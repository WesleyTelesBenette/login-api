package com.wesleytb.project_login.controller;

import com.wesleytb.project_login.dto.UserGetDto;
import com.wesleytb.project_login.dto.UserPostDto;
import com.wesleytb.project_login.dto.UserPutDto;
import com.wesleytb.project_login.service.UserService;
import org.apache.coyote.Response;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserGetDto>> getAllUser() throws ServiceException {
        List<UserGetDto> allUsers = this.userService.getAllUsers();

        return (allUsers.size() > 0)
            ? ResponseEntity.ok(allUsers)
            : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserGetDto> getUserById(@PathVariable Long id) throws ServiceException {
        Optional<UserGetDto> user = this.userService.getUserById(id);

        return user.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserPostDto> createUser(@RequestBody UserPostDto newUser) throws ServiceException {
        UserPostDto user = this.userService.createUser(newUser);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserPutDto> updateUser(@PathVariable Long id, @RequestBody UserPutDto dataUser) throws ServiceException {
        Optional<UserPutDto> user = this.userService.updateUserById(id, dataUser);

        return user.map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws ServiceException {
        boolean deletedUser = this.userService.deleteUserById(id);

        return deletedUser
            ? ResponseEntity.ok().build()
            : ResponseEntity.notFound().build();
    }
}
