package com.example.StaffTransferManagementSpringBoot.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Manager_request")
@Data
public class ManagerReq {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="manager_request_id")
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
//    @Column(name = "Desired_Institution")
//    private String institution;
    @Column(name = "Reason_For_Transfer")
    private String reason_for_transfer;
    @Column(name = "Date_Of_Submission")
    private LocalDate date;
    @Column(name = "Status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "institution_id", referencedColumnName = "institution_id")
    private Institution institution;





}
