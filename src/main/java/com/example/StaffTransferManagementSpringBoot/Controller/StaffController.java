package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import com.example.StaffTransferManagementSpringBoot.Repository.StaffRepository;
import com.example.StaffTransferManagementSpringBoot.Service.InstitutionService;
import com.example.StaffTransferManagementSpringBoot.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/url")
@CrossOrigin(origins = "*")
public class StaffController {
  @Autowired
  private StaffService staffService;
  @Autowired
  private StaffRepository staffRepository;

  @PostMapping("/staff")
  public Staff addStaff(@RequestBody Staff staff){
    return staffService.addStaff(staff);
  }

  @GetMapping("/staff")
  public ResponseEntity<?> getAllStaff(){
    return ResponseEntity.ok(staffService.getAllStaff());
  }
  @GetMapping("/staff/{id}")
  public ResponseEntity<?> getById(@PathVariable int id){
    return  ResponseEntity.ok(staffService.findById(id));
  }

  @PutMapping("/staff/{id}")
  public Staff updateStaff(@RequestBody Staff staff, @PathVariable int id){
    return staffService.updateStaff(staff,id);
  }

  @DeleteMapping("/staff/{id}")
  public ResponseEntity<?> deleteStaff(@PathVariable int id){

    Staff staff = staffRepository.findById(id)
            .orElseThrow( ()-> new ResourceNotFoundException("Id :" +id +" was not found"));

    staffRepository.delete(staff);
    // act as an alert, this is code for message
    // Map used to carry only 2 parameter
    Map<String, Boolean> response = new HashMap<>();
    response.put("Staff with id: "+id +" was deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);

  }
}
