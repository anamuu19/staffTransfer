package com.example.StaffTransferManagementSpringBoot.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Manager")
@Data
public class Manager {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "manager_id")
  private int id;
  private String FirstName;
  private String MiddleName;
  private String LastName;
  private String Email;
  private String PhoneNumber;
  private String Address;
  private String gender;
  @ManyToOne
  @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
  private Institution institution;
}
