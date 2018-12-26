package com.example.common.config;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
        mailSender.setHost("smtp.163.com");
        mailSender.setUsername("untitled_mail@163.com");
        mailSender.setPassword("perth199822");

    }

    public void sendEmail(String email,String title,String text) throws MessagingException {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        StringBuffer messageText=new StringBuffer();//内容以html格式发送,防止被当成垃圾邮件
        messageText.append("<h2>Hi，你好，这是一份充满诚意的提醒，请及时记住您所设置的密码</h2></br>");
        messageText.append("<a>"+text+"<a>");
        //防止成为垃圾邮件，披上outlook的马甲
        //message.addheader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
        // 设置发送者
        message.setFrom(new InternetAddress("untitled_mail@163.com"));
        //message.setFrom("untitled_mail@163.com");
        message.setTo(email);
        message.setSubject(title);
        message.setText(messageText.toString(), "text/html;charset=gb2312");
        this.mailSender.send(mimeMessage);
    }
}
