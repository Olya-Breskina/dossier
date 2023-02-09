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
   private final String sescode;
    @Override
    public void sendMail(EmailMessage emailMessage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("breskinaolya@gmail.com");
        message.setTo("breskinaolya@gmail.com");
        message.setSubject(buildSubject(emailMessage));
        message.setText(buildText(emailMessage,sescode));
        log.info("отправка сообщения пользователю {} по заявке {}",emailMessage.getAddress(), emailMessage.getApplicationId());
        mailSender.send(message);
    }

    private String buildSubject(EmailMessage emailMessage) {
        return "заявка на кредит:" + emailMessage.getApplicationId();
    }

    private String buildText(EmailMessage emailMessage,String sescode) {
        String  messageFoSend = new String();
        switch (emailMessage.getTheme()) {
            case FINISH_REGISTRATION: {
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s одобрена! Теперь вам необходимо завершить регистрацию по следующей ссылке:{http://localhost:8080/swagger-ui/index.html#/Реализация%20кредитного%20конвейера/getOffersPages}", emailMessage.getApplicationId());
            }break;
            case SEND_SES: {
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s введите код подтверждения %s", emailMessage.getApplicationId(), sescode);
            } break;
            case CREDIT_ISSUED: {
                messageFoSend = String.format("Добрый день,вам выдан кредит");
            } break;
            case SEND_DOCUMENTS: {
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s отправить документы", emailMessage.getApplicationId());
            }break;
            case CREATE_DOCUMENTS: {
                messageFoSend = String.format("Здравствуйте, ваша заявка на кредит № %s прошела все проверки", emailMessage.getApplicationId());
            }break;
            case APPLICATION_DENIED:{
                messageFoSend= String.format("Здравствуйте, ваша заявка на кредит № %s отклонена", emailMessage.getApplicationId());
            }break;
            default: messageFoSend= String.format("Добрый день,заявка  отклонена");
        }
        return messageFoSend;
    }
}
