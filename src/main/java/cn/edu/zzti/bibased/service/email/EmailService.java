package cn.edu.zzti.bibased.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发送Email
 */
@Service
public class EmailService {

    @Resource
    private JavaMailSender mailSender;
    @Value("${spring.mail.sendusername}")
    private String emailFrom;
    @Value("${spring.mail.username}")
    private String sendTo;

    public void sendSimpleMail(String titel, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailFrom);
        message.setTo(sendTo);
        message.setSubject(titel);
        message.setText(content);
        mailSender.send(message);
    }
}
