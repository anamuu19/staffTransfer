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
  private Integer id;
  private String firstName;
  private String middleName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String address;
  private String gender;


//  @ManyToOne
//  @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
//  private Institution institution;

//  private String institution;
  @ManyToOne
  @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
  private Institution institution;
}
