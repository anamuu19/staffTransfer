package com.example.StaffTransferManagementSpringBoot.Controller;

import lombok.Data;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/letter/")
public interface LetterGeneratorAPI {
    @GetMapping(value = "individual-letter/{requestId}/{institutionId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateIndividualLetter(@PathVariable("requestId") int requestId, @PathVariable("institutionId") int institutionId);

    @GetMapping(value = "collection-letter", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> generateCollectionLetter();
}
