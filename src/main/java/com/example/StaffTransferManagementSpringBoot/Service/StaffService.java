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

  public Staff addStaff(Staff staff){
    return staffRepository.save(staff);
  }

  public List<?> getAllStaff(){
    return staffRepository.findAll();

  }
  public Optional<?> findById(int id){
    return staffRepository.findById(id);
  }

  public Staff updateStaff(Staff staff,int id){
    Staff staff1 = staffRepository.findById(id).get();
    staff1.setFirstName(staff.getFirstName());
    staff1.setMiddleName(staff.getMiddleName());
    staff1.setAddress(staff.getAddress());
    staff1.setLastName(staff.getLastName());
    staff1.setEmail(staff.getEmail());
    staff1.setPhoneNumber(staff.getPhoneNumber());
    staff1.setGender(staff.getGender());
    staff1.setInstitution(staff.getInstitution());
    Staff updateStaff = staffRepository.save(staff1);
    return updateStaff;
  }

  public Long getCount(){
    return staffRepository.count();
  }

}
