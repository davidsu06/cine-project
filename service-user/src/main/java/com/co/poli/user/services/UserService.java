package com.co.poli.user.services;

import com.co.poli.user.entities.User;

import java.util.List;

public interface UserService {
  User createUser(User user);
  List<User> getAllUsers();
  User getUserFindById(Long id);
  void deleteUser(User user);
}
