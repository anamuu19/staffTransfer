package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Manager;
import com.example.StaffTransferManagementSpringBoot.Repository.InstitutionRepository;
import com.example.StaffTransferManagementSpringBoot.Repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

  @Autowired
  private ManagerRepository managerRepository;

  @Autowired
  private InstitutionRepository institutionRepository;

  public List<Manager> getAllManager() {
    return managerRepository.findAll();
  }

  public Optional<?> findById(int managerID) {
    return managerRepository.findById(managerID);
  }

  public Manager addManager(Manager manager) {
    return managerRepository.save(manager);
  }

  public Manager updateManager(Manager manager, int id) {
    Manager existingManager = managerRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
    existingManager.setFirstName(manager.getFirstName());
    existingManager.setMiddleName(manager.getMiddleName());
    existingManager.setLastName(manager.getLastName());
    existingManager.setAddress(manager.getAddress());
    existingManager.setEmail(manager.getEmail());
    existingManager.setPhoneNumber(manager.getPhoneNumber());
    existingManager.setGender(manager.getGender());

    // Check if the institution exists
    Institution institution = manager.getInstitution();
    if (institution != null) {
      Institution existingInstitution = institutionRepository.findById(institution.getId()).orElse(null);
      if (existingInstitution == null) {
        // Save the new institution
        existingInstitution = institutionRepository.save(institution);
      }
      existingManager.setInstitution(existingInstitution);
    }

    return managerRepository.save(existingManager);
  }

  public Long getCount(){
    return managerRepository.count();
  }
}
