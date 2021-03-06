package com.devglan.controller;

import com.devglan.model.ApiResponse;
import com.devglan.model.Employee;
import com.devglan.model.EmpDto;
import com.devglan.service.EmpService;
import com.devglan.service.impl.EmpServiceImpl;

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
@RequestMapping("/245678342/ighklsd")
public class EmpInfoController {

	private static final Logger logger = LoggerFactory.getLogger(EmpServiceImpl.class);
	
    @Autowired
    private EmpService empService;

    @PostMapping
    public ApiResponse<Employee> saveEmployee(@RequestBody EmpDto emp){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee saved successfully.",empService.save(emp));
    }

    @GetMapping
    public ApiResponse<List<Employee>> listUser(){
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee list fetched successfully.",empService.findAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<Employee> getOne(@PathVariable String id){
    	try {
    	sendmail();
    	logger.info("Email Sent Successfully1");
    	}
    	catch(Exception e) {
    		logger.info("Email Exception: {}",e);
    	}
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee fetched successfully.",empService.findOne(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<EmpDto> update(@RequestBody EmpDto empDto) {
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee updated successfully.",empService.update(empDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable int id) {
    	empService.delete(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Employee deleted successfully.", null);
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
