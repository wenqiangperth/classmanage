package com.example.common.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Calendar;
import java.util.Properties;

/**
 * @ClassName SendMailConfig
 * @Description
 * @Author perth
 * @Date 2018/12/26 0026 下午 10:16
 * @Version 1.0
 **/
public class SendMailConfig {
    public String sendEmail(String title,String text,String email){
        // 1.创建一个程序与邮件服务器会话对象 Session
        Properties props = new Properties();
        // 连接协议
        props.setProperty("mail.transport.protocol", "SMTP");
        // 连接协议
        props.setProperty("mail.smtp.host", "smtp.163.com");
        // 连接协议
        props.setProperty("mail.smtp.port", "25");

        // 指定验证为true
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.timeout","1000");
        // 验证账号及密码，密码需要是第三方授权码
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("perthwangqiang@163.com", "perth1998");
            }
        };
        Session session = Session.getInstance(props, auth);

        // 2.创建一个Message，它相当于是邮件内容
        MimeMessage message = new MimeMessage(session);
        try {
            //防止成为垃圾邮件，披上outlook的马甲
            message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");
            // 设置发送者
            message.setFrom(new InternetAddress("perthwangqiang@163.com"));
            // 设置发送方式与接收者
            message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
            // 设置主题
            message.setSubject(title);

            //创建消息主体
            MimeBodyPart messageBodyPart = new MimeBodyPart();



            messageBodyPart.setText(text);

            // 创建多重消息
            Multipart multipart=new MimeMultipart();

            multipart.addBodyPart(messageBodyPart);

            // 设置邮件消息发送的时间
            message.setSentDate(Calendar.getInstance().getTime());
            // 设置内容
            message.setContent(multipart, "text/html;charset=utf-8");

            // 3.创建 Transport用于将邮件发送
            Transport.send(message);
            return "200";
        }catch (Exception e){
            e.printStackTrace();
            return "400";
        }
    }

}
