package zxy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件服务，发送用的邮箱是163邮箱，使用时需注意在163网站上设置那里获取“授权码”。
 */
@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    private static final String MAIL_CHINESE_NAME = "【zxy】系统";
    private Session mailSession = null;

    @Value("${mail.name}")
    private String mailName;
    @Value("${mail.authorization}")
    private String mailAuthorization;

    @PostConstruct
    public void init() {
        Properties mailPro = new Properties();
        if (logger.isDebugEnabled()) {
            // 开启debug调试
            mailPro.setProperty("mail.debug", "true");
        }
        // 发送服务器需要身份验证
        mailPro.setProperty("mail.smtp.auth", "true");
        // 设置邮件服务器主机名
        mailPro.setProperty("mail.host", "smtp.163.com");
        // 发送邮件协议名称
        mailPro.setProperty("mail.transport.protocol", "smtp");
        // 设置环境信息
        mailSession = Session.getInstance(mailPro);
    }

    /**
     *
     * @param title     标题
     * @param content   内容
     * @param toEamil   接收人
     * @return 是否发送出去
     */
    public boolean send(String title, String content, String toEamil) {
        // 创建邮件对象
        Message msg = new MimeMessage(mailSession);
        try {
            msg.setSubject(title);
            // 设置邮件内容
            msg.setText(content);
            // 设置发件人
            msg.setFrom(new InternetAddress(mailName, MAIL_CHINESE_NAME, "UTF-8"));
        }
        catch (Exception e) {
            logger.error("send set error.", e);
            return false;
        }

        Transport transport = null;
        try {
            transport = mailSession.getTransport();
            // 连接邮件服务器
            transport.connect(mailName, mailAuthorization);
            // 发送邮件
            transport.sendMessage(msg, new Address[]{new InternetAddress(toEamil)});
            return true;
        }
        catch (Exception e) {
            logger.error("send error.", e);
        }
        finally {
            // 关闭连接
            try {
                transport.close();
            }
            catch (MessagingException e) {
                // ignore
            }
        }
        return false;
    }

}
