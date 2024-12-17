package com.workshopPlanning.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import com.workshopPlanning.event.WorkshopEvent;
import com.workshopPlanning.model.MaintenanceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import java.io.File;
import com.itextpdf.layout.Document;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "workshop-plannified")
    public void listen(WorkshopEvent workshopPlanningEvent) {
        log.info("Received WorkshopEvent: {}", workshopPlanningEvent);

        MaintenanceStatus status;
        try {
            status = MaintenanceStatus.valueOf(workshopPlanningEvent.getType().toString().toUpperCase());
        } catch (IllegalArgumentException e) {
            log.error("Invalid maintenance status received: {}", workshopPlanningEvent.getType());
            return;
        }

        if (status == MaintenanceStatus.COMPLETED) {
            handleCompletedStatus(workshopPlanningEvent);
        } else {
            handleOtherStatuses(workshopPlanningEvent, status);
        }
    }

    private void handleCompletedStatus(WorkshopEvent workshopPlanningEvent) {
        // Générer le PDF de la facture
        String pdfFilePath = generateInvoicePDF(workshopPlanningEvent);

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom("zsGarage3@email.com");
            messageHelper.setTo(workshopPlanningEvent.getEmail().toString());
            messageHelper.setSubject("Your maintenance is completed - Invoice");
            messageHelper.setText(String.format("""
                        Hi %s,

                        Your vehicle with registration number %s has completed maintenance. 

                        Please find your invoice attached.

                        Best Regards,
                        zsGarage
                        """,
                    workshopPlanningEvent.getName(),
                    workshopPlanningEvent.getRegistrationNumber()
            ));

            // Joindre le fichier PDF généré
            File invoiceFile = new File(pdfFilePath);
            messageHelper.addAttachment("Invoice.pdf", invoiceFile);
        };

        sendEmail(workshopPlanningEvent, messagePreparator);
    }

    private String generateInvoicePDF(WorkshopEvent workshopPlanningEvent) {
        String pdfFilePath = "invoices/" + workshopPlanningEvent.getRegistrationNumber() + "_invoice.pdf";
        try {
            // Assurez-vous que le répertoire des factures existe
            File directory = new File("invoices");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Configuration pour écrire le PDF
            PdfWriter writer = new PdfWriter(pdfFilePath);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Ajouter du contenu à la facture
            document.add(new Paragraph("Invoice")
                    .setBold()
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Invoice"));
            document.add(new Paragraph(String.format("Date: %s", java.time.LocalDate.now())));
            document.add(new Paragraph("--------------------------------"));
            document.add(new Paragraph(String.format("Customer Name: %s", workshopPlanningEvent.getName())));
            document.add(new Paragraph(String.format("Registration Number: %s", workshopPlanningEvent.getRegistrationNumber())));
            document.add(new Paragraph(String.format("Total Amount: %.2f",  workshopPlanningEvent.getAmount())));
            document.add(new Paragraph("--------------------------------"));
            document.add(new Paragraph("Thank you for choosing zsGarage!"));

            // Fermer le document
            document.close();
            log.info("Invoice PDF generated at: {}", pdfFilePath);

        } catch (Exception e) {
            log.error("Failed to generate PDF invoice", e);
            throw new RuntimeException("Failed to generate PDF invoice", e);
        }

        return pdfFilePath;
    }


    private void handleOtherStatuses(WorkshopEvent workshopPlanningEvent, MaintenanceStatus status) {
        String statusDescription = status.getDescription();

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("zsGarage3@email.com");
            messageHelper.setTo(workshopPlanningEvent.getEmail().toString());
            messageHelper.setSubject(String.format("Your vehicle with registration number %s has been updated", workshopPlanningEvent.getRegistrationNumber()));
            messageHelper.setText(String.format("""
                            Hi %s,

                            Your vehicle with registration number %s has a new status update: %s

                            Status Description: %s

                            Best Regards,
                            zsGarage
                            """,
                    workshopPlanningEvent.getName(),
                    workshopPlanningEvent.getRegistrationNumber(),
                    status.name(),
                    statusDescription
            ));
        };

        sendEmail(workshopPlanningEvent, messagePreparator);
    }

    private String generateInvoice(String registrationNumber, Double amount) {
        return String.format("Invoice for job %s: Total amount = %.2f", registrationNumber, amount);
    }

    private void sendEmail(WorkshopEvent workshopPlanningEvent, MimeMessagePreparator messagePreparator) {
        try {
            javaMailSender.send(messagePreparator);
            log.info("Notification email sent for registration number: {}", workshopPlanningEvent.getRegistrationNumber());
        } catch (MailException e) {
            log.error("Exception occurred when sending mail to {}", workshopPlanningEvent.getEmail(), e);
            throw new RuntimeException("Exception occurred when sending mail", e);
        }
    }
}
