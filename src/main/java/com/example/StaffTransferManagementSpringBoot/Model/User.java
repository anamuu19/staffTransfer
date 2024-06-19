package com.example.StaffTransferManagementSpringBoot.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
  @Id
  @Column(name = "Email" ,length = 100)
  private String email;

  @Column(name = "First Name")
  private String firstName;

  @Column(name = "Middle Name")
  private String middleName;

  @Column(name = "Last Name")
  private String lastName;

  @Column(name = "Telephone Number")
  private String telNo;

  @Column(name = "Password")
  private String password;

  @Column(name = "Confirm Password")
  private String confirm;

  @Column(name = "Institution")
  private String instInfo;

  @Column(name = "Gender")
  private String gender;

  @Column(name = "Position")
  private String position;

  @Column(name = "Role")
  private String role;

  @Column(name = "Token")
  private String token;
}
