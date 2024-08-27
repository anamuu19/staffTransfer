package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.User;
import com.example.StaffTransferManagementSpringBoot.Repository.UserRepository;
import com.example.StaffTransferManagementSpringBoot.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class UserController {
  @Autowired
  private UserService userService;
  @Autowired
  private UserRepository userRepository;
  @PostMapping("/register")
  public User Register(@RequestBody User user){
    return userService.register(user);
//    return result;
  }
  @PostMapping("/login")
  public User login(@RequestBody User user){

    return userService.login(user);
//    return token;

  }
  @GetMapping("/getAll")
  public ResponseEntity<?> getAllUser(){
    return ResponseEntity.ok(userService.getAllUser());
  }

  @GetMapping("/count")
  public ResponseEntity<?> getCount(){
    return ResponseEntity.ok(userService.getCount());
  }

//  @PostMapping("/user/post")
//  public User addUser(@RequestBody User user){
//    return userService.addUser(user);
//  }

  @PutMapping("/user/put/{id}")
  public User updateUser(@RequestBody User user, @PathVariable String id){
    return userService.updateUser(user,id);
  }

  @DeleteMapping("/user/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable String id){

    User user = userRepository.findById(id)
      .orElseThrow( ()-> new ResourceNotFoundException("Id :" +id +" was not found"));

    userRepository.delete(user);
    // act as an alert, this is code for message
    // Map used to carry only 2 parameter
    Map<String, Boolean> response = new HashMap<>();
    response.put("Institution with id: "+id +" was deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);

  }

  @GetMapping("/user/{id}")
  public ResponseEntity<?> getById(@PathVariable String id){
    return  ResponseEntity.ok(userService.findById(id));
  }

}

