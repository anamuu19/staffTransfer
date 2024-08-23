package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Repository.InstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class InstitutionService {

  @Autowired
  private InstitutionRepository institutionRepository;

  public Institution addInstitution(Institution institution){
    return institutionRepository.save(institution);
  }

  public List<?> getAllInstitution(){
    return institutionRepository.findAll();

  }

  public Optional<Institution> findById(int institutionId){
    return institutionRepository.findById(institutionId);
  }



  public Institution updateInstitution(Institution institution,int institutionId){
    Institution inst = institutionRepository.findById(institutionId).get();
    inst.setName(institution.getName());
    inst.setAddress(institution.getAddress());
//    inst.setDepartment(institution.getDepartment());
    inst.setEmail(institution.getEmail());
    inst.setPhoneNumber(institution.getPhoneNumber());
    Institution updateInstitution = institutionRepository.save(inst);
    return updateInstitution;
  }

//  public void deleteInstitutionById(int id) {
//    institutionRepository.deleteById(id);
//  }

  public Long getCount(){
    return institutionRepository.count();
  }
  public Optional<Integer> getInstitutionIdByName(String name) {
    Optional<Institution> institution = institutionRepository.findByName(name);
    return institution.map(Institution::getId);
  }


}
