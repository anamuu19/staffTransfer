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
  private String FirstName;
  @Column(name = "LastName")
  private String LastName;
  @Column(name = "Email")
  private String Email;
  @Column(name = "Address")
  private String Address;
  @Column(name = "PhoneNumber")
  private String PhoneNumber;
  private String gender;
  @ManyToOne
  @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
  private Institution institution;

}
