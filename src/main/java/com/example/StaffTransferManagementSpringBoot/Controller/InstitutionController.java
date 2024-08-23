package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Repository.InstitutionRepository;
import com.example.StaffTransferManagementSpringBoot.Service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/url")
@CrossOrigin(origins = "*")
public class InstitutionController {
  @Autowired
  private InstitutionService institutionService;
  @Autowired
  private InstitutionRepository institutionRepository;


  @DeleteMapping("/institution/{id}")
  public ResponseEntity<?> deleteInstitution(@PathVariable int id){

    Institution institution = institutionRepository.findById(id)
      .orElseThrow( ()-> new ResourceNotFoundException("Id :" +id +" was not found"));

    institutionRepository.delete(institution);
    // act as an alert, this is code for message
    // Map used to carry only 2 parameter
    Map<String, Boolean> response = new HashMap<>();
    response.put("Institution with id: "+id +" was deleted", Boolean.TRUE);
    return ResponseEntity.ok(response);

  }

  @PostMapping("/institution")
  public Institution addInstitution(@RequestBody Institution institution){
    return institutionService.addInstitution(institution);
  }

  @GetMapping("/institution")
  public ResponseEntity<?> getAllInstitution(){
    return ResponseEntity.ok(institutionService.getAllInstitution());
  }

  @GetMapping("/institution/{id}")
  public ResponseEntity<?> getById(@PathVariable int id){
    return  ResponseEntity.ok(institutionService.findById(id));
  }

  @PutMapping("/institution/{id}")
  public Institution updateInstitution(@RequestBody Institution institution, @PathVariable int id){
    return institutionService.updateInstitution(institution,id);
  }

  @GetMapping("/institution/count")
  public ResponseEntity<?> getCount(){
    return ResponseEntity.ok(institutionService.getCount());
  }

  @GetMapping("/institution/name/{name}")
  public ResponseEntity<Integer> getInstitutionIdByName(@PathVariable String name) {
    Optional<Integer> institutionId = institutionService.getInstitutionIdByName(name);
    return institutionId.map(id -> ResponseEntity.ok(id))
            .orElseGet(() -> ResponseEntity.notFound().build());
  }



}
