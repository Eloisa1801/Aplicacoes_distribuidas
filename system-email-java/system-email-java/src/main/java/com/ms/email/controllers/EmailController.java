package com.ms.email.controllers;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sending-email")
public class EmailController {

    @Autowired
    EmailService emailService;

    @PostMapping
    public ResponseEntity<EmailModel> sendingEmail(@RequestBody @Valid EmailDto emailDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        return new ResponseEntity<>(emailService.sendEmail(emailModel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmailModel>> getAllEmails() {
        List<EmailModel> emailsList = emailService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(emailsList);
    }
}
