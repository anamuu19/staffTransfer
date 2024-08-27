package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.User;
import com.example.StaffTransferManagementSpringBoot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
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
    // Encrypt the password
    String password = user.getPassword();
    String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes(StandardCharsets.UTF_16BE));
    user.setPassword(encryptedPassword);

    // Encrypt the confirm password
    String confirmPassword = user.getConfirm();
    String encryptedConfirmPassword = Base64.getEncoder().encodeToString(confirmPassword.getBytes(StandardCharsets.UTF_16BE));
    user.setConfirm(encryptedConfirmPassword);

    // Set a token for the user
    user.setToken(generateToken());

    // Save the user to the repository
    return userRepository.save(user);
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
    // Fetch the existing user from the database using the email
    User existingUser = userRepository.findById(user.getEmail()).orElse(null);

    // Check if the user exists
    if (existingUser != null) {
      // Encrypt the password entered during login
      String encryptedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes(StandardCharsets.UTF_16BE));

      // Compare the encrypted password with the stored password
      if (existingUser.getPassword().equals(encryptedPassword) &&
              existingUser.getRole().equals(user.getRole())) {

        // Clear the password before returning the user object
        existingUser.setPassword("");
        return existingUser;
      }
    }

    // Return null if authentication fails
    return null;
  }

  public List<User> getAllUser() {
    return userRepository.findAll();
  }

  public Optional<User> findById(String id) {
    return userRepository.findById(id);
  }

  public Long getCount() {
    return userRepository.count();
  }

  public User updateUser(User user, String id) {
    User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));

    // Update the user details
    existingUser.setEmail(user.getEmail());
    existingUser.setFirstName(user.getFirstName());
    existingUser.setMiddleName(user.getMiddleName());
    existingUser.setLastName(user.getLastName());
    existingUser.setTelNo(user.getTelNo());

    // Encrypt the password if it's being updated
    if (user.getPassword() != null && !user.getPassword().isEmpty()) {
      String encryptedPassword = Base64.getEncoder().encodeToString(user.getPassword().getBytes(StandardCharsets.UTF_16BE));
      existingUser.setPassword(encryptedPassword);
    }

    // Encrypt the confirm password if it's being updated
    if (user.getConfirm() != null && !user.getConfirm().isEmpty()) {
      String encryptedConfirmPassword = Base64.getEncoder().encodeToString(user.getConfirm().getBytes(StandardCharsets.UTF_16BE));
      existingUser.setConfirm(encryptedConfirmPassword);
    }

    existingUser.setInstInfo(user.getInstInfo());
    existingUser.setGender(user.getGender());
    existingUser.setPosition(user.getPosition());
    existingUser.setRole(user.getRole());

    return userRepository.save(existingUser);
  }
}
