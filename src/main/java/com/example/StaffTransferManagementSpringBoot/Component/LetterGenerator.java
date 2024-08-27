package com.example.StaffTransferManagementSpringBoot.Component;

import com.example.StaffTransferManagementSpringBoot.Model.Request;
import com.example.StaffTransferManagementSpringBoot.Model.Institution;
import com.example.StaffTransferManagementSpringBoot.Repository.RequestRepository;
import com.example.StaffTransferManagementSpringBoot.Repository.InstitutionRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Service
public class LetterGenerator {

    private final RequestRepository requestRepository;
    private final InstitutionRepository institutionRepository;

    public LetterGenerator(RequestRepository requestRepository, InstitutionRepository institutionRepository) {
        this.requestRepository = requestRepository;
        this.institutionRepository = institutionRepository;
    }

    public ByteArrayInputStream generateIndividualLetter(int reqId, int instId) {
        // Fetch the request and institution details
        Request request = requestRepository.findById(reqId)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        Institution institution = institutionRepository.findById(instId)
                .orElseThrow(() -> new RuntimeException("Institution not found"));

        // Extract staff details
        String fName = request.getFirstName();
        String mName = request.getMiddleName();
        String lName = request.getLastName();
        String phone = request.getPhoneNumber();
        String staffEmail = request.getEmail();
        String address = request.getAddress();
        String currentInstitution = request.getCurrent_institution();
        String desiredInstitution = institution.getName();

        // Set the formatted date
        LocalDate requestDate = request.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = requestDate.format(formatter);

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Setting up fonts and formatting
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, BaseColor.BLACK);
            Font fontB = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);

            // Add logo image
            Image jpg = Image.getInstance(getClass().getResource("/static/logo.jpg"));
            jpg.scaleAbsolute(150, 110);
            jpg.setAlignment(Element.ALIGN_LEFT);

            // Create the header table with two columns for name and details
            PdfPTable headerTable = new PdfPTable(2);
            headerTable.setWidthPercentage(100);
            headerTable.setWidths(new int[]{1, 2}); // Set the width ratio for columns

            // Add the logo to the first cell
            PdfPCell logoCell = new PdfPCell(jpg);
            logoCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(logoCell);

            // Create a nested table for the right side content
            PdfPTable detailsTable = new PdfPTable(1);
            detailsTable.setWidthPercentage(100);

            // Add the name row
            PdfPCell nameCell = new PdfPCell(new Phrase(fName + " " + mName + " " + lName, font));
            nameCell.setBorder(Rectangle.NO_BORDER);
            nameCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            detailsTable.addCell(nameCell);

            // Add the address row
            PdfPCell addressCell = new PdfPCell(new Phrase("P.O.Box: 000\n" + address, font));
            addressCell.setBorder(Rectangle.NO_BORDER);
            addressCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            detailsTable.addCell(addressCell);

            // Add the phone number row
            PdfPCell phoneCell = new PdfPCell(new Phrase(phone, font));
            phoneCell.setBorder(Rectangle.NO_BORDER);
            phoneCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            detailsTable.addCell(phoneCell);

            // Add the email row
            PdfPCell emailCell = new PdfPCell(new Phrase(staffEmail, font));
            emailCell.setBorder(Rectangle.NO_BORDER);
            emailCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            detailsTable.addCell(emailCell);

            // Add the date row
            PdfPCell dateCell = new PdfPCell(new Phrase("Date: " + formattedDate, font));
            dateCell.setBorder(Rectangle.NO_BORDER);
            dateCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            detailsTable.addCell(dateCell);

            // Add the details table to the right cell of the main table
            PdfPCell detailsCell = new PdfPCell(detailsTable);
            detailsCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(detailsCell);

            // Add the header table to the document
            document.add(headerTable);

            // Adding a space
            document.add(new Paragraph("\n"));

            // Add body text with institution and request details
            Paragraph body = new Paragraph("Ref No.: SMZ/ADM/BCS/18/19/001\n\n" +
                    institution.getName() + "\nP.O.Box: " + institution.getAddress() + "\n" +
                    "Phone: " + institution.getPhoneNumber() + "\n" +
                    "Email: " + institution.getEmail() + "\n\nDear Sir/Madam,\n", font);
            body.setAlignment(Element.ALIGN_LEFT);
            document.add(body);

            Paragraph subject = new Paragraph("\nREF: REQUEST FOR TRANSFER", fontB);
            subject.setAlignment(Element.ALIGN_CENTER);
            document.add(subject);

            // Details about the staff/request
            Phrase staffName = new Phrase(fName + " " + mName + " " + lName, fontB);
            Paragraph content = new Paragraph();
            content.add(new Phrase("This letter is to notify you that "));
            content.add(staffName);
            content.add(new Phrase(", currently working at " + currentInstitution +
                    ", has submitted a request to transfer to " + desiredInstitution + ".\n" +
                    "The request has been reviewed and approved by the administration. " +
                    "Please kindly proceed with the necessary arrangements to facilitate this transfer.\n\n" +
                    "Yours faithfully,\n", font));

            content.setAlignment(Element.ALIGN_LEFT);
            document.add(content);

            // Signature and closing
            Image sign = Image.getInstance(getClass().getResource("/static/sign.jpg"));
            sign.scaleAbsolute(80, 50);
            sign.setAlignment(Element.ALIGN_LEFT);
            document.add(sign);

            Paragraph closing = new Paragraph("Dr. Adnaan,\nFor DVC Academics Research and Consultancy Services\n" +
                    "Serikali ya Mapinduzi Zanzibar(SMZ).", font);
            closing.setAlignment(Element.ALIGN_LEFT);
            document.add(closing);

            document.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Failed to generate letter.", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
