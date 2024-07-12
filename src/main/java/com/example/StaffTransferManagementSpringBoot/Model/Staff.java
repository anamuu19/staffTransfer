package com.example.StaffTransferManagementSpringBoot.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Staff")
@Data
public class Staff {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "staff_id")
  private int id;

  @Column(name = "FirstName")
  private String firstName;
  @Column(name = "MiddleName")
  private String middleName;
  @Column(name = "LastName")
  private String lastName;
  @Column(name = "Email")
  private String email;
  @Column(name = "Address")
  private String address;
  @Column(name = "PhoneNumber")
  private String phoneNumber;
  @Column(name = "Gender")
  private String gender;
  @Column(name = "Institution")
  private String institution;


//  @ManyToOne
//  @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
//  private Institution institution_id;

}
