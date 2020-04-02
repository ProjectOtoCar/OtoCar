package com.otocar.otocar.controller;

import com.otocar.otocar.model.Mail;
import com.otocar.otocar.service.MailSenderService;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;

@Controller
public class MailController {

    private MailSenderService mailSenderService;

    public MailController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }

    @PostMapping("/mail")
    ResponseEntity<?> send (@RequestBody Mail mail) throws MessagingException {
        String subject = "Wiadomość od: "+mail.getMailSender()+" \n"+mail.getContent();
        mailSenderService.sendMail(mail.getMail(),mail.getTitle(),subject,mail.isHtmlContent());

        return ResponseEntity.ok(mail);
    }

}
