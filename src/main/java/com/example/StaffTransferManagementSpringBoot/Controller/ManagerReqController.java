package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.ManagerReq;
import com.example.StaffTransferManagementSpringBoot.Model.Request;
import com.example.StaffTransferManagementSpringBoot.Repository.ManagerReqRepository;
import com.example.StaffTransferManagementSpringBoot.Service.ManagerReqService;
import com.example.StaffTransferManagementSpringBoot.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/url/manager")
@CrossOrigin(origins = "*")
public class ManagerReqController {

    @Autowired
    private ManagerReqService managerReqService;
    @Autowired
    private ManagerReqRepository managerReqRepository;

    @PostMapping("/request")
    public ManagerReq addRequest(@RequestBody ManagerReq managerReq){
        return managerReqService.addRequest(managerReq);
    }

    @GetMapping("/request")
    public ResponseEntity<?> getAllRequest(){
        return ResponseEntity.ok(managerReqService.getAllRequest());
    }
    @GetMapping("/request/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return  ResponseEntity.ok(managerReqService.findById(id));
    }

    @PutMapping("/request/{id}")
    public ManagerReq updateRequest(@RequestBody ManagerReq request, @PathVariable int id){
        return managerReqService.updateRequest(request,id);
    }


//    @PutMapping("")

//    @PutMapping("/confirm/{id}")
//    public ResponseEntity<?> confirmRequest(@PathVariable int id, @RequestBody ManagerReq request) {
//        ManagerReq updatedRequest = managerReqService.confirmRequest(id, request);
//        return ResponseEntity.ok(updatedRequest);
//    }


    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable int id, @RequestBody ManagerReq request) {
        ManagerReq updatedRequest = managerReqService.rejectRequest(id, request);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/request/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable int id){

        ManagerReq request = managerReqRepository.findById(id)
                .orElseThrow( ()-> new ResourceNotFoundException("Id :" +id +" was not found"));

        managerReqRepository.delete(request);
        // act as an alert, this is code for message
        // Map used to carry only 2 parameter
        Map<String, Boolean> response = new HashMap<>();
        response.put("Request with id: "+id +" was deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/request/count")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok(managerReqService.getCount());
    }


}
