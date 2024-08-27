package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Exception.ResourceNotFoundException;
import com.example.StaffTransferManagementSpringBoot.Model.Request;
import com.example.StaffTransferManagementSpringBoot.Repository.RequestRepository;
import com.example.StaffTransferManagementSpringBoot.Service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/url")
@CrossOrigin(origins = "*")
public class RequestController {

    @Autowired
    private RequestService requestService;
    @Autowired
    private RequestRepository requestRepository;
//    @Autowired
//    private ConfirmedRequestService confirmedRequestService;
//    @Autowired
//    private SimpMessagingTemplate template;

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
//    @GetMapping("/requests/status/{staffId}")
//    public ResponseEntity<?> getRequestStatus(@PathVariable int staffId) {
//        List<Request> requests = requestService.getRequestsByStaffId(staffId);
//        return ResponseEntity.ok(requests);
//    }




    @PutMapping("/request/{id}")
    public Request updateRequest(@RequestBody Request request, @PathVariable int id){
        return requestService.updateRequest(request,id);
    }

    @PutMapping("/confirm/{id}")
    public ResponseEntity<?> confirmRequest(@PathVariable int id, @RequestBody Request request) {
        Request updatedRequest = requestService.confirmRequest(id, request);
        return ResponseEntity.ok(updatedRequest);
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<?> rejectRequest(@PathVariable int id, @RequestBody Request request) {
        try {
            Request updatedRequest = requestService.rejectRequest(id, request);
            return ResponseEntity.ok(updatedRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/accepted")
    public List<Request> getAcceptedRequests() {
        return requestRepository.findByStatus("accepted");
    }


//    @PutMapping("/confirm/{id}")
//    public ResponseEntity<?> confirmRequest(@PathVariable("id") int id, @RequestBody ConfirmedRequest confirmedRequest) {
//        try {
//            confirmedRequestService.confirmRequest(id, confirmedRequest);
//            Request request = requestRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("Request not found with id " + id));
//
//            String notificationMessage = "Your request for staff transfer has been confirmed.";
//
//            // Send notification to the manager
//            template.convertAndSend("/topic/notifications", notificationMessage);
//
//            return ResponseEntity.ok().build();
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }



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

    @GetMapping("/request/count")
    public ResponseEntity<?> getCount(){
        return ResponseEntity.ok(requestService.getCount());
    }

    @PostMapping("/api/requests/{id}/accept")
    public ResponseEntity<?> acceptRequest(@PathVariable int id) {
        try {
            requestService.acceptRequest(id);
            return ResponseEntity.ok().body("Request accepted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/requests/user/{email}")
    public List<Request> getRequestsByUserEmail(@PathVariable String email) {
        return requestService.getRequestsByEmail(email);
    }






}
