package com.co.poli.user.services;

import com.co.poli.user.entities.User;
import com.co.poli.user.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServicesImp implements UserService {
  private final UserRepository userRepository;

  @Override
  public User getUserFindById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public void deleteUser(User user) {
    userRepository.delete(user);
  }
}
