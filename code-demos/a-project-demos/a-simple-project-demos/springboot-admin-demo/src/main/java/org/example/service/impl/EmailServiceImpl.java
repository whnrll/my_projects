package org.example.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.example.service.IEmailService;
import org.example.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements IEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.from}")
    private String defaultFrom;

    @Override
    public void sendEmail(String from, String to, String subject, String content) {
        if (StringUtils.isBlank(from)) {
            from = null;
        }

        if (!RegexUtil.mathches(from, RegexUtil.EMAIL)) {
            from = defaultFrom;
        }

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
        log.info("send message success, subject: [{}]", subject);
    }
}
