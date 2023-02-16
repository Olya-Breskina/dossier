package ru.podgoretskaya.dossier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.podgoretskaya.dossier.dto.EmailMessage;

@Component
@Slf4j
@RequiredArgsConstructor
public class DossierConsumer {
    private final MailSender mailSender;

    @KafkaListener(topics = "finish-registration", groupId = "dossier")
    public void finishRegistration(String message) {
        log.info("получено сообщение:{}", message);
        EmailMessage emailMessage = messageToSend(message);

        mailSender.sendMail(emailMessage);
    }

    @KafkaListener(topics = "create-documents", groupId = "dossier")
    public void createDocuments(String message) {
        log.info("получено сообщение:{}", message);
        EmailMessage emailMessage = messageToSend(message);
        mailSender.sendMail(emailMessage);
    }

    @KafkaListener(topics = "send-documents", groupId = "dossier")
    public void sendDocuments(String message) {
        log.info("получено сообщение:{}", message);
        EmailMessage emailMessage = messageToSend(message);
        mailSender.sendMail(emailMessage);
    }

    @KafkaListener(topics = "send-ses", groupId = "dossier")
    public void sendSes(String message) {
        log.info("получено сообщение:{}", message);
        EmailMessage emailMessage = messageToSend(message);
        mailSender.sendMail(emailMessage);
    }

    @KafkaListener(topics = "credit-issued", groupId = "dossier")
    public void creditIssued(String message) {
        log.info("получено сообщение:{}", message);
        EmailMessage emailMessage = messageToSend(message);
        mailSender.sendMail(emailMessage);
    }

    @KafkaListener(topics = "application-denied", groupId = "dossier")
    public void applicationDenied(String message) {
        log.info("получено сообщение:{}", message);
        EmailMessage emailMessage = messageToSend(message);
        mailSender.sendMail(emailMessage);
    }

    private EmailMessage messageToSend(String message) {
        try {
            return new ObjectMapper().readValue(message, EmailMessage.class);
        } catch (Exception e) {
            log.info("ошибка отпавки сообщения:{}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }

}
