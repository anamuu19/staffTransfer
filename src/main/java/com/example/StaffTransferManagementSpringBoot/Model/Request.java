package com.example.StaffTransferManagementSpringBoot.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Request")
@Data

public class Request {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="request_id")
  private int id;

  @ManyToOne
  @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
  private Staff staff;
  @ManyToOne
  @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
  private Institution institution;
  @ManyToOne
  @JoinColumn(name = "manager_id",referencedColumnName = "manager_id")
  private Manager manager;

  @Column(name = "Reason_For_Submission")
  private String Reason_For_Submission;
  @Column(name = "Date_Of_Submission")
  private String Date;

  private String Status;

}
