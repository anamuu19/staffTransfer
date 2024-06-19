package com.example.StaffTransferManagementSpringBoot.Request;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class RequestDto {
  private String FirstName;
  private String LastName;
  private String Email;
  private String PhoneNumber;
  private String Address;
  private int institutionId;
}
