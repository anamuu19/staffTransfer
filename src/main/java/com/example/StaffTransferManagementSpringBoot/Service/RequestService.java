package com.example.StaffTransferManagementSpringBoot.Service;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.Request;
//import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import com.example.StaffTransferManagementSpringBoot.Repository.RequestRepository;
import com.example.StaffTransferManagementSpringBoot.Repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    public RequestRepository requestRepository;

//    @Autowired
//    private StaffRepository staffRepository;

//    public List<Request> getRequestsByStaffId(int staffId) {
//        Staff staff = staffRepository.findById(staffId)
//                .orElseThrow(() -> new IllegalArgumentException("Invalid staff ID"));
//        return requestRepository.findAllByStaff(staff);
//    }

    public Request addRequest(Request request){
        return requestRepository.save(request);
    }

    public List<?> getAllRequest(){
        return requestRepository.findAll();

    }
//    public List<Request> getRequestsByStaffId(int id) {
//        return requestRepository.findAllByStaffId(id);
//    }

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
//        req.setCurrent_institution(request.getCurrent_institution());
        req.setInstitution(request.getInstitution());
        req.setReason_for_transfer(request.getReason_for_transfer());
        req.setDate(request.getDate());

        Request updateRequest = requestRepository.save(req);
        return updateRequest;

    }



    public Request confirmRequest(int id,Request request) {
        Request existingRequest = requestRepository.findById(id).orElseThrow();
        existingRequest.setStatus("accepted");  // Admin confirms final acceptance
        return requestRepository.save(existingRequest);
    }

    public Request rejectRequest(int id, Request request) {
        Request existingRequest = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + id));
        existingRequest.setStatus("rejected");
        return requestRepository.save(existingRequest);
    }


    public Long getCount(){
        return requestRepository.count();
    }
    public void acceptRequest(int id) {
        Request request = requestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
        request.setStatus("accepted");
        requestRepository.save(request);
    }
    public List<Request> getRequestsByEmail(String email) {
        return requestRepository.findByEmail(email);
    }







}
