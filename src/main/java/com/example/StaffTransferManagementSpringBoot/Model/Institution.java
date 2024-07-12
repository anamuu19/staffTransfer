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
  private String name;
  private String address;
  private String email;
  private String phoneNumber;


}
