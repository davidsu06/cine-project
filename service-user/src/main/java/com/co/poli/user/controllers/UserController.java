package com.co.poli.user.controllers;

import com.co.poli.user.entities.User;
import com.co.poli.user.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
  private final UserService userService;

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result){
    if (result.hasErrors()) {
      return ResponseEntity.badRequest().build();
    }

    userService.createUser(user);
    return ResponseEntity.ok(user);
  }

  @GetMapping
  public ResponseEntity<List<User>> getAllUsers(){
    List<User> allUsers = userService.getAllUsers();

    if (allUsers.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(allUsers);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
    User user = userService.getUserFindById(id);

    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
    User user = userService.getUserFindById(id);

    if (user == null) {
      return ResponseEntity.notFound().build();
    }

    userService.deleteUser(user);
    return ResponseEntity.ok(user);
  }
}
