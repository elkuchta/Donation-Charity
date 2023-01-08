package pl.coderslab.charity.mail;

import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {

    void sendSimpleMessageTwo( String text,String to);

    void sendSimpleMessage(String subject, String text);

    void sendActivationEmail(String to,  String text);

    void sendRemindPasswordEmail(String to, String text);
}
