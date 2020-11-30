//https://www.callicoder.com/spring-boot-task-scheduling-with-scheduled-annotation/
//https://java2blog.com/java-timer-example/
/*******************************************************
 * This code ise used to update the token once evry 5 sec
 *  To start the timer - 
 *      Timer timer=new Timer(); //  task will be scheduled after 5 sec delay
 *      timer.schedule(new MyTimerTask(timer), 5000);
 * 
 */


package com.devglan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Component
public class TmrTask {
    private static final Logger logger = LoggerFactory.getLogger(TmrTask.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static int count=0;
    public static String Token="";

    //public void scheduleTaskWithFixedRate() {}

    public void scheduleTaskWithFixedDelay() {}

    public void scheduleTaskWithInitialDelay() {}

    public void scheduleTaskWithCronExpression() {}
    
    
    @Scheduled(fixedRate = 2000)
    public void scheduleTaskWithFixedRate() {
//        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
  	  if(count==0) {
  		  count=2;
		  //timer.cancel();
		  //get a new TOKEN;
	        // Generate random number between 5 to 30  
	        double a = 5 + (Math.random() * 30000000);  
	        long p=(long )a;
	        Token = String.valueOf(p);
	        logger.info("Token Is: {}",Token);
	  }
	  else {
		  --count;
	        /*double a = 5 + (Math.random() * 30000000);  
	        long p=(long )a;
	        Token = String.valueOf(p);
	        logger.info("Token Is: {}",Token);*/
	  }
  
        
    } 
    
}


/*
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
public class TmrTask extends TimerTask{
 
 public int count;
 public String Token;

 private static final Logger logger = LoggerFactory.getLogger(VstServiceImpl.class);
 
 Timer timer;
 public TmrTask(Timer timer) {
  this.timer=timer;
  count=0;
 }
 
 
 
 @Override
 public void run() {
	  // to stop timer thread
	  if(count==0) {
		  //timer.cancel();
		  //get a new TOKEN;
	        // Generate random number between 5 to 30  
	        double a = 5 + (Math.random() * 30000000);  
	        long p=(long )a;
	        Token = String.valueOf(p);
	        logger.info("Token Is: {}",Token);
	  }
	  else {
		  --count;
	        double a = 5 + (Math.random() * 30000000);  
	        long p=(long )a;
	        Token = String.valueOf(p);
	        logger.info("Token Is: {}",Token);
	  }
  
 }
 
}*/
 