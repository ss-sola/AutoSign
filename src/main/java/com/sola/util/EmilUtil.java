package com.sola.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * ToDo
 *
 * @Author sola
 * @Date 2022/6/26 14:42
 */
@Component
public class EmilUtil {

    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}"+"@qq.com")
    String send;


    public  void contextLoads(String receive,String text,String title) throws MessagingException {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

            //标题
            helper.setSubject(title);
            //内容
            helper.setText(text);
            //邮件接收者
            helper.setTo(receive);
            //邮件发送者，必须和配置文件里的一样，不然授权码匹配不上
            helper.setFrom(send);
            mailSender.send(mimeMessage);

    }

}
