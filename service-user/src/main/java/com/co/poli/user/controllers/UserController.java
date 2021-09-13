package com.co.poli.user.controllers;

import com.co.poli.user.dto.UserDto;
import com.co.poli.user.entities.User;
import com.co.poli.user.services.UserService;
import com.example.multimodule.service.CommonService;
import com.example.multimodule.service.utils.Response;
import com.example.multimodule.service.utils.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
  private final UserService userService;
  private final ResponseBuilder responseBuilder;
  private final CommonService commonService;
  private final ModelMapper modelMapper;

  @PostMapping
  public Response createUser(@Valid @RequestBody UserDto userDto, BindingResult result){
    if (result.hasErrors()) {
      return responseBuilder.failed(commonService.formatMessage(result));
    }

    User user = modelMapper.map(userDto,User.class);
    userService.createUser(user);
    UserDto userDtoRespuesta = modelMapper.map(user,UserDto.class);

    return responseBuilder.success(userDtoRespuesta);
  }

  @GetMapping
  public Response getAllUsers(){
    List<User> allUsers = userService.getAllUsers();

    if (allUsers.isEmpty()) {
      return responseBuilder.failed(null);
    }

    List<UserDto> userDtos = allUsers.stream()
            .map(user -> modelMapper.map(user,UserDto.class))
            .collect(Collectors.toList());

    return responseBuilder.success(userDtos);
  }

  @GetMapping("/{id}")
  public Response getUserById(@PathVariable("id") Long id) {
    User user = userService.getUserFindById(id);

    if (user == null) {
      return responseBuilder.failed(null);
    }

    UserDto userDto = modelMapper.map(user,UserDto.class);

    return responseBuilder.success(userDto);
  }

  @DeleteMapping("/{id}")
  public Response deleteUser(@PathVariable("id") Long id) {
    User user = userService.getUserFindById(id);

    if (user == null) {
      return responseBuilder.failed(null);
    }

    userService.deleteUser(user);
    UserDto userDto = modelMapper.map(user,UserDto.class);

    return responseBuilder.success(userDto);
  }
}
