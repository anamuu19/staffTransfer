package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.User;
import com.example.StaffTransferManagementSpringBoot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.BasicPermission;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  private static final SecureRandom secureRandom = new SecureRandom();
  private static final Base64.Encoder base64encoder = Base64.getUrlEncoder();

  public User register(User user) {

//    return userRepository.save(user);

    //check if user already exist
    if(checkUserExist(user)==true)
      return null;
    user.setToken(generateToken());

    return userRepository.save(user);
//    return "user registration successfully";
  }

  private String generateToken() {

    byte[] token = new byte[24];
    secureRandom.nextBytes(token);
    return base64encoder.encodeToString(token);

  }

  private boolean checkUserExist(User user) {
    return userRepository.findById(user.getEmail()).isPresent();
  }


  public User login(User user) {
    User existingUser = userRepository.findById(user.getEmail()).orElse(null);
    if (existingUser != null &&
      existingUser.getEmail().equals(user.getEmail()) &&
      existingUser.getPassword().equals(user.getPassword()) &&
      existingUser.getRole().equals(user.getRole())) {
      existingUser.setPassword("");
      return existingUser;
    }
    return null;
  }

  public List<?> getAllUser(){
    return userRepository.findAll();

  }

  public Optional<User> findById(String id){
    return userRepository.findById(id);
  }

  public Long getCount(){
    return userRepository.count();
  }

  public User updateUser(User user,String id){
    User user1 = userRepository.findById(id).get();
    user1.setEmail(user.getEmail());
    user1.setFirstName(user.getFirstName());
    user1.setMiddleName(user.getMiddleName());
    user1.setLastName(user.getLastName());
    user1.setTelNo(user.getTelNo());
    user1.setPassword(user.getPassword());
    user1.setConfirm(user.getConfirm());
    user1.setInstInfo(user.getInstInfo());
    user1.setGender(user.getGender());
    user1.setPosition(user.getPosition());
    user1.setRole(user.getRole());

    User updateUser = userRepository.save(user1);
    return updateUser;
  }

//  public User addUser(User user){
//    return userRepository.save(user);
//  }


}
