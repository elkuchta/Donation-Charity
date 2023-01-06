package pl.coderslab.charity.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.user.UserRepository;

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

    public void sendActivationEmail(String to, String text){

        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("charity.donation.chd@gmail.com");
        message.setTo (to);
        message.setSubject("Link do aktywacji konta");
        message.setText(text);

        emailSender.send(message);
    }

    public void sendRemindPasswordEmail(String to, String text){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom("charity.donation.chd@gmail.com");
        message.setTo (to);
        message.setSubject("Link do zmiany hasła");
        message.setText(text);

        emailSender.send(message);
    }

}
