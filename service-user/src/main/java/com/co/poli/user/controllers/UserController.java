package com.co.poli.user.controllers;

import com.co.poli.user.entities.User;
import com.co.poli.user.services.UserService;
import com.example.multimodule.service.MyService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
  private final UserService userService;
  private final ResponseBuilder responseBuilder;
  private final MyService myService;

  @PostMapping
  public Response createUser(@Valid @RequestBody User user, BindingResult result){
    if (result.hasErrors()) {
      return responseBuilder.failed(myService.formatMessage(result));
    }

    userService.createUser(user);
    return responseBuilder.success(user);
  }

  @GetMapping
  public Response getAllUsers(){
    List<User> allUsers = userService.getAllUsers();

    if (allUsers.isEmpty()) {
      return responseBuilder.failed(null);
    }

    return responseBuilder.success(allUsers);
  }

  @GetMapping("/{id}")
  public Response getUserById(@PathVariable("id") Long id) {
    User user = userService.getUserFindById(id);

    if (user == null) {
      return responseBuilder.failed(null);
    }

    return responseBuilder.success(user);
  }

  @DeleteMapping("/{id}")
  public Response deleteUser(@PathVariable("id") Long id) {
    User user = userService.getUserFindById(id);

    if (user == null) {
      return responseBuilder.failed(null);
    }

    userService.deleteUser(user);
    return responseBuilder.success(user);
  }
}
