package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import com.example.StaffTransferManagementSpringBoot.Service.InstitutionService;
import com.example.StaffTransferManagementSpringBoot.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/url")
public class StaffController {
  @Autowired
  private StaffService staffService;

  @PostMapping("/staff")
  public String addStaff(@RequestBody Staff staff){
    return staffService.addStaff(staff);
  }

  @GetMapping("/staff")
  public ResponseEntity<?> getAllStaff(){
    return ResponseEntity.ok(staffService.getAllStaff());
  }
  @GetMapping("/staff/{staffId}")
  public ResponseEntity<?> getById(@PathVariable int staffId){
    return  ResponseEntity.ok(staffService.findById(staffId));
  }
}
