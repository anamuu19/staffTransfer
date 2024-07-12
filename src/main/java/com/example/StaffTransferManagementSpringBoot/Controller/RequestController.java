package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.Request;
import com.example.StaffTransferManagementSpringBoot.Model.Staff;
import com.example.StaffTransferManagementSpringBoot.Repository.RequestRepository;
import com.example.StaffTransferManagementSpringBoot.Repository.StaffRepository;
import com.example.StaffTransferManagementSpringBoot.Service.RequestService;
import com.example.StaffTransferManagementSpringBoot.Service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/url")
@CrossOrigin(origins = "*")
public class RequestController {

    @Autowired
    private RequestService requestService;
    @Autowired
    private RequestRepository requestRepository;

    @PostMapping("/request")
    public Request addRequest(@RequestBody Request request){
        return requestService.addRequest(request);
    }

    @GetMapping("/request")
    public ResponseEntity<?> getAllRequest(){
        return ResponseEntity.ok(requestService.getAllRequest());
    }
    @GetMapping("/request/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return  ResponseEntity.ok(requestService.findById(id));
    }

    @PutMapping("/request/{id}")
    public Request updateRequest(@RequestBody Request request, @PathVariable int id){
        return requestService.updateRequest(request,id);
    }

    @DeleteMapping("/request/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id){

        Request request = requestRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Id :" +id +" was not found"));

        requestRepository.delete(request);
        // act as an alert, this is code for message
        // Map used to carry only 2 parameter
        Map<String, Boolean> response = new HashMap<>();
        response.put("Request with id: "+id +" was deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }




}
