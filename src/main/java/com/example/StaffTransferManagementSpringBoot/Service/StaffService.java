package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import com.example.StaffTransferManagementSpringBoot.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
  @Autowired
  public StaffRepository staffRepository;

  public String addStaff(Staff staff){
    staffRepository.save(staff);
    return "success";
  }

  public List<?> getAllStaff(){
    return staffRepository.findAll();

  }
  public Optional<?> findById(int staffId){
    return staffRepository.findById(staffId);
  }

  public Staff updateStaff(Staff staff,int staffId){
    Staff staff1 = staffRepository.findById(staffId).get();
    staff1.setFirstName(staff.getFirstName());
    staff1.setAddress(staff.getAddress());
    staff1.setLastName(staff.getLastName());
    staff1.setEmail(staff.getEmail());
    staff1.setPhoneNumber(staff.getPhoneNumber());
    Staff updateStaff = staffRepository.save(staff1);
    return updateStaff;
  }

}
