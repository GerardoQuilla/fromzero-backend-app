package com.jgerardo.fromzeroapi.iam.application.internal.outboundservices.acl;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.angus.mail.util.MailConnectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    @Value("${email-service.verification-url}")
    private String verificationUrl;

    private final JavaMailSender mailSender;

    private final TemplateEngine templateEngine;

    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.mailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public CompletableFuture<Void> sendEmail(String username, String token) {
        Context context = new Context();
        context.setVariable("verificationUrl", verificationUrl+token);
        context.setVariable("name", username);

        String body = templateEngine.process("email-template", context);
        try {
            var mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(username);
            helper.setSubject("Cambio de contraseña");
            helper.setText(body, true);
            helper.setFrom(sender);

            mailSender.send(mimeMessage);
            log.info("Email sent to {}", username);

            return CompletableFuture.completedFuture(null);

        }catch (MailAuthenticationException e){
            log.error("Email authentication failed. {}", e.getMessage());
            return CompletableFuture.failedFuture(e);
        } catch (MailConnectException e) {
            log.error("Could not connect to SMTP server. {}", e.getMessage());
            return CompletableFuture.failedFuture(e);
        } catch (MessagingException e) {
            log.error("Error during email sending. {}", e.getMessage());
            return CompletableFuture.failedFuture(e);
        }
    }

}
