package com.workshopPlanning.service;

import com.workshopPlanning.event.WorkshopPlacedEvent;
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
        public void listen(WorkshopPlacedEvent workshopPlanningEvent){
            System.out.println("im listen");
            log.info("get message from workshop-plannified",workshopPlanningEvent);
            MimeMessagePreparator messagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
                messageHelper.setFrom("zsGarage3@email.com");
                messageHelper.setTo(workshopPlanningEvent.getEmail());
                messageHelper.setSubject(String.format("Your vehicle with registrationNumber %s is placed successfully", workshopPlanningEvent.getRegistrationNumber()));
                messageHelper.setText(String.format("""
                            Hi Dear

                            Your Vehicle with registration number %s is now plannified in a workshop successfully.

                            Best Regards
                            zsGarage
                            """,
                        workshopPlanningEvent.getRegistrationNumber()));
            };
            try {
                javaMailSender.send(messagePreparator);
                log.info("Order Notifcation email sent!!");
            } catch (MailException e) {
                log.error("Exception occurred when sending mail", e);
                throw new RuntimeException("Exception occurred when sending mail to springshop@email.com", e);
            }
        }
    }


