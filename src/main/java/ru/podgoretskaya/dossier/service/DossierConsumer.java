package ru.podgoretskaya.dossier.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.podgoretskaya.dossier.dto.EmailMessage;

@Component
@Slf4j
public class DossierConsumer {
    @KafkaListener(topics = "finish-registration", groupId = "dossier")
    public void finishRegistration(String message) throws Exception  {
        log.info("получено сообщение:{}", message);
        messageToSend(message);
    }

    @KafkaListener(topics = "create-documents", groupId = "dossier")
    public void createDocuments(String message) throws Exception  {
        log.info("получено сообщение:{}", message);
        messageToSend(message);
    }

    @KafkaListener(topics = "send-documents", groupId = "dossier")
    public void sendDocuments(String message) throws Exception  {
        log.info("получено сообщение:{}", message);
        messageToSend(message);
    }

    @KafkaListener(topics = "send-ses", groupId = "dossier")
    public void sendSes(String message) throws Exception  {
        log.info("получено сообщение:{}", message);
        messageToSend(message);
    }

    @KafkaListener(topics = "credit-issued", groupId = "dossier")
    public void creditIssued(String message) throws Exception  {
        log.info("получено сообщение:{}", message);
        messageToSend(message);
    }

    @KafkaListener(topics = "application-denied", groupId = "dossier")
    public void applicationDenied(String message) throws Exception {
        log.info("получено сообщение:{}", message);
        messageToSend(message);
    }
    private void messageToSend(String message) throws Exception {
        try {
            EmailMessage emailMessage = new ObjectMapper().readValue(message, EmailMessage.class);
        } catch (Exception e) {
            log.info("ошибка отпавки сообщения");
            throw new IllegalArgumentException();
        }
    }
}
