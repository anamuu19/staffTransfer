package com.example.StaffTransferManagementSpringBoot.Controller;

import com.example.StaffTransferManagementSpringBoot.Component.LetterGenerator;
import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Model.Request;
import com.example.StaffTransferManagementSpringBoot.Repository.InstitutionRepository;
import com.example.StaffTransferManagementSpringBoot.Repository.RequestRepository;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/letter")
public class LetterGeneratorController {

    private final LetterGenerator letterGenerator;
    private final RequestRepository requestRepository;
    private final InstitutionRepository institutionRepository;

    public LetterGeneratorController(LetterGenerator letterGenerator, RequestRepository requestRepository, InstitutionRepository institutionRepository) {
        this.letterGenerator = letterGenerator;
        this.requestRepository = requestRepository;
        this.institutionRepository = institutionRepository;
    }

    @GetMapping("/individual-letter/{requestId}/{institutionId}")
    public ResponseEntity<?> generateIndividualLetter(@PathVariable int requestId, @PathVariable int institutionId) {
        try {
            ByteArrayInputStream bis = letterGenerator.generateIndividualLetter(requestId, institutionId);

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=transfer_letter.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));
        } catch (Exception ex) {
            return ResponseEntity.status(409).body("Something went wrong: " + ex.getMessage());
        }
    }

}
