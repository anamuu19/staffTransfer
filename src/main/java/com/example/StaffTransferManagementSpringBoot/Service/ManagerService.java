package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Manager;
import com.example.StaffTransferManagementSpringBoot.Repository.ManagerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

  @Autowired
  private ManagerRepository managerRepository;

//  @Autowired
//  private ModelMapper modelMapper;

  public List<Manager> getAllManager(){
    return managerRepository.findAll();

  }
  public Optional<?> findById(int managerID){
    return managerRepository.findById(managerID);
  }

  public String addManager(Manager manager){
    managerRepository.save(manager);
    return "success";
  }

  public Manager updateManager(Manager manager,int id){
    Manager manager1 = managerRepository.findById(id).get();
    manager1.setFirstName(manager.getFirstName());
    manager1.setLastName(manager.getLastName());
    manager1.setAddress(manager.getAddress());
    manager1.setEmail(manager.getEmail());
    manager1.setPhoneNumber(manager.getPhoneNumber());
    Manager updateManager = managerRepository.save(manager1);
    return updateManager;
  }

}
