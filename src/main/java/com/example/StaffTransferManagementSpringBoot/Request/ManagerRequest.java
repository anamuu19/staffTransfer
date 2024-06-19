package com.example.StaffTransferManagementSpringBoot.Request;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ManagerRequest {


  private String FirstName;
  private String MiddleName;
  private String LastName;
  private String Email;
  private String PhoneNumber;
  private String Address;
  private String gender;
  private int institutionId;


}
