package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.Visitor;
import com.devglan.model.VstDto;
import com.devglan.service.VstService;
import com.devglan.service.impl.VstServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/245678342/ighklsd1")
public class VstInfoController {

	private static final Logger logger = LoggerFactory.getLogger(VstServiceImpl.class);
	
    @Autowired
    private VstService vstService;

    @PostMapping
    public ApiResponse<Visitor> saveVisitor(@RequestBody VstDto vst){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor saved successfully.",vstService.save(vst));
    }

    @GetMapping
    public ApiResponse<List<Visitor>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor list fetched successfully.",vstService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Visitor> getOne(@PathVariable String id){
    	try {
    	sendmail();
    	logger.info("Email Sent Successfully1");
    	}
    	catch(Exception e) {
    		logger.info("Email Exception: {}",e);
    	}
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor fetched successfully.",vstService.findOne(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<VstDto> update(@RequestBody VstDto vstDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor updated successfully.",vstService.update(vstDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	vstService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor deleted successfully.", null);
    }

    @RequestMapping(value = "/sendemail")
    public String sendEmail() throws AddressException, MessagingException, IOException {
       sendmail();
       return "Email sent successfully";   
    }
    
    //https://www.tutorialspoint.com/spring_boot/spring_boot_sending_email.htm
    private void sendmail() throws AddressException, MessagingException, IOException {

    	/*
    	   Properties props = new Properties();
    	   props.put("mail.smtp.auth", "true");
    	   props.put("mail.smtp.starttls.enable", "true");
    	   props.put("mail.smtp.host", "smtp.gmail.com");
    	   props.put("mail.smtp.port", "587");
    	   
    	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
//     	         return new PasswordAuthentication("tutorialspoint@gmail.com", "<your password>");
    	         return new PasswordAuthentication("naga1.pdi@gmail.com", "protodesign");
    	      }
    	   });
    	   Message msg = new MimeMessage(session);
    	   msg.setFrom(new InternetAddress("sailurao@yahoo.com", false));

    	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sailurao@yahoo.com"));
    	   msg.setSubject("Tutorials point email");
    	   msg.setContent("Tutorials point email", "text/html");
    	   msg.setSentDate(new Date());

    	   MimeBodyPart messageBodyPart = new MimeBodyPart();
    	   messageBodyPart.setContent("Tutorials point email", "text/html");

    	   Multipart multipart = new MimeMultipart();
    	   multipart.addBodyPart(messageBodyPart);
    	   MimeBodyPart attachPart = new MimeBodyPart();

    	   //attachPart.attachFile("/var/tmp/image19.png");
    	   //multipart.addBodyPart(attachPart);
    	   msg.setContent(multipart);
    	   Transport.send(msg);   
    	   
    	   logger.info("Email Sent succesfully2");*/
    	}
}
