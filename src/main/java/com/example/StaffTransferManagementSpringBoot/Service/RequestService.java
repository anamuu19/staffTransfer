package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Model.Request;
//import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import com.example.StaffTransferManagementSpringBoot.Repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    public RequestRepository requestRepository;

    public Request addRequest(Request request){
        return requestRepository.save(request);
    }

    public List<?> getAllRequest(){
        return requestRepository.findAll();

    }

    public Optional<?> findById(int id){
        return requestRepository.findById(id);
    }

    public Request updateRequest(Request request,int id) {
        Request req = requestRepository.findById(id).get();
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

        Request updateRequest = requestRepository.save(req);
        return updateRequest;

    }





}
