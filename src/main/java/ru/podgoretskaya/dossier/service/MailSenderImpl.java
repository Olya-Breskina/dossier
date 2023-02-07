package ru.podgoretskaya.dossier.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.dossier.dto.EmailMessage;
@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
   private final JavaMailSender  mailSender;
    @Override
    public void sendMail(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("breskinaolya@gmail.com");
        message.setTo("breskinaolya@gmail.com");
        message.setSubject(buildSubject(emailMessage));
        message.setText(buildText(emailMessage));
        log.info("отправка сообщения пользователю {} по заявке {}",emailMessage.getAddress(), emailMessage.getApplicationId());
        mailSender.send(message);
    }

    private String buildSubject(EmailMessage emailMessage) {
        return "заявка на кредит:" + emailMessage.getApplicationId();
    }

    private String buildText(EmailMessage emailMessage) {
        return String.format("Добрый день,заявки с № %s: %s", emailMessage.getApplicationId(), emailMessage.getTheme());
    }
}
