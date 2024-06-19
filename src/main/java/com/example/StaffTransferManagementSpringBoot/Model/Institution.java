package com.example.StaffTransferManagementSpringBoot.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "institution")
@Data
public class Institution {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "institution_id")
  private int id;
  private String Name;
  private String Address;
  private String Email;
  private String PhoneNumber;


}
