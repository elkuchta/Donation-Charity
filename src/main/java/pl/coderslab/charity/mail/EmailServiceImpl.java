package pl.coderslab.charity.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String subject, String text
           ) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("charity.donation.chd@gmail.com");
        message.setTo("charity.donation.chd@gmail.com");
        message.setSubject(subject);
        message.setText(text);

        emailSender.send(message);
    }
}
