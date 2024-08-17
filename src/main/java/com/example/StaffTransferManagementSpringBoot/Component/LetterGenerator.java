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

        String fName = request.getFirstName();
        String mName = request.getMiddleName();
        String lName = request.getLastName();
        String currentInstitution = request.getCurrent_institution();
        String desiredInstitution = request.getInstitution();

        // Set the formatted date
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        String formattedDate = currentDate.format(formatter);

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
            jpg.scaleAbsolute(150, 110); // Increased width and height
            jpg.setAlignment(Element.ALIGN_LEFT);

            // Create header with institution details
            Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL); // Renamed font variable for header

            // Create the header paragraph
            Paragraph header = new Paragraph("THE STATE UNIVERSITY OF ZANZIBAR\nP.O.BOX 146\n" +
                    "Tel: 255-24-2230724/2233337\nFax: 255-24-2233337\nZanzibar-Tanzania\n" +
                    "Email: vc@suza.ac.tz\nWeb site: www.suza.ac.tz", headerFont);
            header.setAlignment(Element.ALIGN_RIGHT);

            // Create a table to align logo and header
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setWidths(new int[] {1, 3}); // Adjust column widths as needed

            // Add the logo image to the first cell
            PdfPCell cell = new PdfPCell(jpg);
            cell.setBorder(Rectangle.NO_BORDER);
            table.addCell(cell);

            // Add the header to the second cell
            cell = new PdfPCell(header);
            cell.setBorder(Rectangle.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);

            // Add the table to the document
            document.add(table);

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

            // Details about the student/request
            Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

// Details about the staff/request
            Phrase staffName = new Phrase(fName + " " + mName + " " + lName, boldFont);
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
                    "The State University of Zanzibar (SUZA).", font);
            closing.setAlignment(Element.ALIGN_LEFT);
            document.add(closing);

            document.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Failed to generate letter.", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
