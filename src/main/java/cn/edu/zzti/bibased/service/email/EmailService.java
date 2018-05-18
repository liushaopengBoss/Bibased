package cn.edu.zzti.bibased.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 发送Email
 */
@Service
public class EmailService {
    Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Resource
    JavaMailSenderImpl mailSender;

    @Async
    public void sendSimpleMail(String titel, String content) {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo("1396647019@qq.com");//收件人邮箱地址
            mail.setFrom("biggress@163.com");//发件人
            mail.setSubject(titel);//主题
            mail.setText(content);//正文
            mailSender.send(mail);
        }catch (Exception e){
            logger.info("邮件发送失败-->"+titel+": " +content);
        }

    }
}
