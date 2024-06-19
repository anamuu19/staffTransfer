package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Manager;
import com.example.StaffTransferManagementSpringBoot.Request.ManagerRequest;
import com.example.StaffTransferManagementSpringBoot.Service.InstitutionService;
import com.example.StaffTransferManagementSpringBoot.Service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/url")
public class ManagerController {

  @Autowired
  private ManagerService managerService;
  @Autowired
  private InstitutionService institutionService;
  @Autowired
  private ModelMapper modelMapper;

  @PostMapping("/manager")
  public String addManager(@RequestBody ManagerRequest manager){
    Manager i = new Manager();
    Optional<Institution> inst = institutionService.findById(manager.getInstitutionId());
           i =modelMapper.map(manager,Manager.class);
    if(inst.isPresent()){
      i.setInstitution(inst.get());

    }
    return   managerService.addManager(i);
  }
  @GetMapping("/manager")
  public ResponseEntity<?> getAllManager(){
    return ResponseEntity.ok(managerService.getAllManager());
  }
  @GetMapping("/manager/{id}")
  public ResponseEntity<?> getById(@PathVariable int id){
    return  ResponseEntity.ok(managerService.findById(id));
  }
  @PutMapping("/manager/{id}")
  public Manager updateManager(@RequestBody Manager manager, @PathVariable int id){
    return managerService.updateManager(manager,id);
  }
}
