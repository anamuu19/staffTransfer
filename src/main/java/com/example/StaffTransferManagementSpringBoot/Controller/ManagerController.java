package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Manager;
import com.example.StaffTransferManagementSpringBoot.Repository.ManagerRepository;
import com.example.StaffTransferManagementSpringBoot.Request.ManagerRequest;
import com.example.StaffTransferManagementSpringBoot.Service.InstitutionService;
import com.example.StaffTransferManagementSpringBoot.Service.ManagerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/url")
@CrossOrigin(origins = "*")
public class ManagerController {

  @Autowired
  private ManagerService managerService;
  @Autowired
  private ManagerRepository managerRepository;
//  @Autowired
//  private ModelMapper modelMapper;

//    @PostMapping("/manager")
//    public ResponseEntity<Manager> addManager(@RequestBody ManagerRequest managerRequest) {
//        Manager manager = modelMapper.map(managerRequest, Manager.class);
//        Optional<Institution> inst = institutionService.findById(managerRequest.getInstitutionId());
//        if (inst.isPresent()) {
//            manager.setInstitution(inst.get());
//        }
//        // Set the id to null to ensure it is treated as a new entity
//        manager.setId(null);
//        Manager savedManager = managerService.addManager(manager);
//        return ResponseEntity.ok(savedManager);
//    }

  @PostMapping("/manager")
  public Manager addManager(@RequestBody Manager manager){
    return managerService.addManager(manager);
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

  @DeleteMapping("/manager/{id}")
  public ResponseEntity<?> deleteManager(@PathVariable int id){

    Manager manager = managerRepository.findById(id)
            .orElseThrow( ()-> new ResourceNotFoundException("Id :" +id +" was not found"));

    managerRepository.delete(manager);
    // act as an alert, this is code for message
    // Map used to carry only 2 parameter
    Map<String, Boolean> response = new HashMap<>();
    response.put("Manager with id: "+id +" was deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);

  }
}
