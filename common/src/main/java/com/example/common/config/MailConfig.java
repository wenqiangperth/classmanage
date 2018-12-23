package com.example.common.config;

import javax.mail.internet.MimeMessage;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
/**
 * @ClassName MailConfig
 * @Description
 * @Author perth
 * @Date 2018/12/23 0023 上午 8:57
 * @Version 1.0
 **/
public class MailConfig {

    JavaMailSenderImpl mailSender;

    public MailConfig()
    {
        mailSender= new JavaMailSenderImpl();
        mailSender.setHost("smtp.126.com");
        mailSender.setUsername("wangqiangperth@126.com");
        mailSender.setPassword("perth199822");

    }

    public void sendEmail(String email,String title,String text) throws MessagingException {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        message.setFrom("wangqiangperth@126.com");
        message.setTo(email);
        message.setSubject(title);
        message.setText(text);
        this.mailSender.send(mimeMessage);
    }
}
