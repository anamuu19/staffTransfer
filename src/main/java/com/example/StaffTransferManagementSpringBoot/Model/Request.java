package com.example.StaffTransferManagementSpringBoot.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Request")
@Data

public class Request {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="request_id")
  private int id;

  @Column(name = "First_name")
  private String firstName;
  @Column(name = "Middle_name")
  private String middleName;
  @Column(name = "Last_name")
  private String lastName;
  @Column(name = "Email")
  private String email;
  @Column(name = "Phone_Number")
  private String phoneNumber;
  @Column(name = "Address")
  private String address;
  @Column(name = "Gender")
  private String gender;

  @Column(name = "Current_Institution")
  private String current_institution;
//  @Column(name = "Desired_Institution")
//  private String institution;
  @Column(name = "Reason_For_Transfer")
  private String reason_for_transfer;
  @Column(name = "Date_Of_Submission")
  private LocalDate date;
  @Column(name = "Comment")
  private String comment;
  @Column(name = "Status")
  private String status;

//  @ManyToOne
//  @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
//  private Staff staff;


//  public String getCurrent_institution() {
//    return current_institution;
//  }
//
//  public void setCurrent_institution(String current_institution) {
//    this.current_institution = current_institution;
//  }


//  @ManyToOne
//  @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
//  private Staff staff;

  @ManyToOne
  @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
  private Institution institution;
//  @ManyToOne
//  @JoinColumn(name = "manager_id",referencedColumnName = "manager_id")
//  private Manager manager;



}
