package org.example.service;

/**
 * 描述：邮件服务
 *
 * @author xutao
 * @Date 2023-02-26 18:30:26
 */
public interface IEmailService {
    /**
     * 描述：发送电子邮件
     *
     * @param from    发件人
     * @param to      接收方
     * @param subject 主题
     * @param content 内容
     */
    void sendEmail(String from, String to, String subject, String content);
}
