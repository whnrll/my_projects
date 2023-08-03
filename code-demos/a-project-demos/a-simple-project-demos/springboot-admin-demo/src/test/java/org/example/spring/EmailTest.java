package org.example.spring;

import org.example.BaseSpringTest;
import org.example.service.IEmailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailTest extends BaseSpringTest {
    @Autowired
    private IEmailService emailService;

    @Test
    public void sendEmail1() {
        String to = "1184206178@qq.com";
        String subject = "这是一封测试邮件";
        String content = "Hi, Nice Girl!";
        emailService.sendEmail(null, to, subject, content);
     }

    @Test
    public void sendEmail2() {
        String to = "1184206178@qq.com";
        String subject = "这是一封未知测试邮件";
        String content = "Hi, Nice Girl!";
        emailService.sendEmail("xxx", to, subject, content);
    }
}
