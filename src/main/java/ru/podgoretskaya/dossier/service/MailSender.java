package ru.podgoretskaya.dossier.service;

import ru.podgoretskaya.dossier.dto.EmailMessage;

public interface MailSender {
    void sendMail(EmailMessage emailMessage);
}
