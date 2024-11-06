package com.workshopPlanning.service;

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
            status = MaintenanceStatus.valueOf(workshopPlanningEvent.getType().toString());
        } catch (IllegalArgumentException e) {
            log.error("Invalid maintenance status received: {}", workshopPlanningEvent.getType());
            return;
        }
        String statusDescription = status.getDescription();

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("zsGarage3@email.com");
            messageHelper.setTo(workshopPlanningEvent.getEmail().toString());
            messageHelper.setSubject(String.format("Your vehicle with registration number %s has been updated", workshopPlanningEvent.getRegistrationNumber()));
            messageHelper.setText(String.format("""
                            Hi Dear , %s,

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

        // Send the email
        try {
            javaMailSender.send(messagePreparator);
            log.info("Notification email sent for registration number: {}", workshopPlanningEvent.getRegistrationNumber());
        } catch (MailException e) {
            log.error("Exception occurred when sending mail", e);
            throw new RuntimeException("Exception occurred when sending mail", e);
        }
    }
}
