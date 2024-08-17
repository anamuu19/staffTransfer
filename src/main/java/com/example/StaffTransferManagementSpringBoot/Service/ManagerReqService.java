package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.ManagerReq;
import com.example.StaffTransferManagementSpringBoot.Model.Request;
import com.example.StaffTransferManagementSpringBoot.Repository.ManagerReqRepository;
import com.example.StaffTransferManagementSpringBoot.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerReqService {

    @Autowired
    public ManagerReqRepository managerReqRepository;

    public ManagerReq addRequest(ManagerReq managerReq){
        return managerReqRepository.save(managerReq);
    }

    public List<?> getAllRequest(){
        return managerReqRepository.findAll();

    }

    public Optional<?> findById(int id){
        return managerReqRepository.findById(id);
    }

    public ManagerReq updateRequest(ManagerReq request,int id) {
        ManagerReq req = managerReqRepository.findById(id).get();
        req.setFirstName(request.getFirstName());
        req.setMiddleName(request.getMiddleName());
        req.setAddress(request.getAddress());
        req.setLastName(request.getLastName());
        req.setEmail(request.getEmail());
        req.setPhoneNumber(request.getPhoneNumber());
        req.setGender(request.getGender());
        req.setCurrent_institution(request.getCurrent_institution());
        req.setInstitution(request.getInstitution());
        req.setReason_for_transfer(request.getReason_for_transfer());
        req.setDate(request.getDate());

        ManagerReq updateRequest = managerReqRepository.save(req);
        return updateRequest;

    }

//    public ManagerReq confirmRequest(int id, ManagerReq managerReq) {
//        ManagerReq existingRequest = managerReqRepository.findById(id).orElseThrow();
//        existingRequest.setStatus("process");  // Manager sets to 'Process' for admin confirmation
//        return managerReqRepository.save(existingRequest);
//    }
//    public String acceptRequest(int id){
//        Optional<ManagerReq> optionalManagerReq = managerReqRepository.findById(id);
//        if(optionalManagerReq.isPresent()){
//            ManagerReq existingRequest = optionalManagerReq.get();
//            existingRequest.setStatus("accepted");
//            managerReqRepository.save(existingRequest);
//            return "Request accepted";
//        }
//        else {
//            return "request not found";
//        }
//    }

    public ManagerReq rejectRequest(int id, ManagerReq managerReq) {
        ManagerReq existingRequest = managerReqRepository.findById(id).orElseThrow();
        existingRequest.setStatus("rejected");
        return managerReqRepository.save(existingRequest);
    }

    public Long getCount(){
        return managerReqRepository.count();
    }
}
