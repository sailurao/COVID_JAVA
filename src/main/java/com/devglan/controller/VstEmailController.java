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

import java.net.*; 
import java.io.*; 
import java.util.*; 
import java.net.InetAddress;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/visitors/email")
public class VstEmailController {

	private static final Logger logger = LoggerFactory.getLogger(VstServiceImpl.class);
	private Visitor my_vst;
	
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
    public ApiResponse<Visitor> getOne(@PathVariable int id){
    	try {
    	  my_vst = vstService.findById(id);
    	sendmail();
    	logger.info("Email Sent Successfully1");
    	}
    	catch(Exception e) {
    		logger.info("Email Exception: {}",e);
    	}
        return new ApiResponse<>(HttpStatus.OK.value(), "Visitor fetched successfully.",my_vst);
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
    	
    	
      	  InetAddress localhost = InetAddress.getLocalHost();
      	  String ip_str=(localhost.getHostAddress()).trim();
          logger.info("System Ip: "+ip_str);
          
          
          // Find public IP address 
          String systemipaddress = ""; 
          try
          { 
              URL url_name = new URL("http://bot.whatismyipaddress.com"); 
              BufferedReader sc = 
              new BufferedReader(new InputStreamReader(url_name.openStream())); 
              // reads system IPAddress 
              systemipaddress = sc.readLine().trim(); 
          } 
          catch (Exception e) 
          { 
              systemipaddress = "Cannot Execute Properly"; 
          } 
          logger.info("Public IP Address: "+systemipaddress);
          
          ip_str = systemipaddress;
          
    	   Properties props = new Properties();
    	   props.put("mail.smtp.auth", "true");
    	   /*   	   props.put("mail.smtp.starttls.enable", "true");
    	   props.put("mail.smtp.host", "smtp.gmail.com");
    	   props.put("mail.smtp.port", "587"); */
    	   
    	    	   props.put("mail.smtp.starttls.enable", "false");
    	   props.put("mail.smtp.host", "mail.teampdi.com");
    	   props.put("mail.smtp.port", "587"); 
    	   
    	   Session session = Session.getInstance(props, new javax.mail.Authenticator() {
    	      protected PasswordAuthentication getPasswordAuthentication() {
     	         return new PasswordAuthentication("donot.reply001@teampdi.com", "Proto2020!!!!");
 //   	         return new PasswordAuthentication("naga1.pdi@gmail.com", "protodesign");
    	      }
    	   });
    	   Message msg = new MimeMessage(session);
    	   msg.setFrom(new InternetAddress(my_vst.getEmail(), false));

    	   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(my_vst.getEmail()));
    	   msg.setSubject("PDI COVID Questionnaire Link");
//    	   String EMPLOYEE1_API_BASE_URL = " http://localhost:3000/new-tr/"; //used to fetch single visitor record by user id
 //   	   String EMPLOYEE1_API_BASE_URL = " http://192.168.0.200:3000/new-tr/"; //used to fetch single visitor record by user id
//   	   String EMPLOYEE1_API_BASE_URL = " http://34.123.142.12:3000/new-tr/"; //used to fetch single visitor record by user id
   	       String EMPLOYEE1_API_BASE_URL = " http://" + ip_str+":3000/new-vst-tr/"; //used to fetch single visitor record by user id

    	   String body_msg = "Please clik on the link";
    	   body_msg +=  EMPLOYEE1_API_BASE_URL+ my_vst.getUserid();
    	   msg.setContent(body_msg, "text/html");
    	   msg.setSentDate(new Date());

    	   MimeBodyPart messageBodyPart = new MimeBodyPart();
    	   messageBodyPart.setContent(body_msg, "text/html");

    	   Multipart multipart = new MimeMultipart();
    	   multipart.addBodyPart(messageBodyPart);
    	   MimeBodyPart attachPart = new MimeBodyPart();

    	   //attachPart.attachFile("/var/tmp/image19.png");
    	   //multipart.addBodyPart(attachPart);
    	   msg.setContent(multipart);
    	   Transport.send(msg);   
    	   
    	   logger.info("Email Sent succesfully2");
    	}
}
