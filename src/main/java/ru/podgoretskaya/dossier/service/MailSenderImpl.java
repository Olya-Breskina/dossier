package ru.podgoretskaya.dossier.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.podgoretskaya.dossier.client.ConveyorClient;
import ru.podgoretskaya.dossier.dto.EmailMessage;

@Service
@Slf4j
@RequiredArgsConstructor
public class MailSenderImpl implements MailSender {
    private final JavaMailSender mailSender;
    private final ConveyorClient conveyorClient;


    @Override
    public void sendMail(EmailMessage emailMessage) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("breskinaolya@gmail.com");
        message.setTo("breskinaolya@gmail.com");
        message.setSubject(buildSubject(emailMessage));
        message.setText(buildText(emailMessage));
        log.info("отправка сообщения пользователю {} по заявке {}", emailMessage.getAddress(), emailMessage.getApplicationId());
        mailSender.send(message);
    }

    private String buildSubject(EmailMessage emailMessage) {
        return "заявка на кредит:" + emailMessage.getApplicationId();
    }

    private String buildText(EmailMessage emailMessage) {
        String messageFoSend = "";

        switch (emailMessage.getTheme()) {
            case FINISH_REGISTRATION:
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s одобрена! ", emailMessage.getApplicationId());
                break;
            case SEND_SES:
                String sesCode = conveyorClient.getApplication(emailMessage.getApplicationId()).getSesCode();
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s введите код подтверждения %s", emailMessage.getApplicationId(), sesCode);
                break;
            case CREDIT_ISSUED:
                messageFoSend = "Добрый день,вам выдан кредит";
                break;
            case SEND_DOCUMENTS:
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s отправить документы", emailMessage.getApplicationId());
                break;
            case CREATE_DOCUMENTS:
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s прошела все проверки", emailMessage.getApplicationId());
                break;
            case APPLICATION_DENIED:
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s отклонена", emailMessage.getApplicationId());
                break;
            default:
                messageFoSend = "Добрый день,заявка  отклонена";
        }
        return messageFoSend;
    }
}
