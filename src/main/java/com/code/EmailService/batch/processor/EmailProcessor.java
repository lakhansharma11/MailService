package com.code.EmailService.batch.processor;

import java.sql.Date;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.code.EmailService.email.EmailServiceImpl;
import com.code.EmailService.entity.EmailTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailProcessor implements ItemProcessor<EmailTask, EmailTask> {

    @Autowired
    EmailServiceImpl emailService;

    @Override
    public EmailTask process(EmailTask ET) throws Exception{
    	
        log.info("processor: {}", ET);
        try {
        	
        	ET.setEMAIL_CONTENT("EmailText.html");
            emailService.sendSimpleMessage(ET.getCustomer_MailID(), "Test mails to ...............",ET.getEMAIL_CONTENT());
            ET.setSTATUS(1L);
            ET.setEmail_SENT_TIMESTAMP(new Date(System.currentTimeMillis() ));
        } catch (Exception sendFailedException) {
            
			log.info("error: {}", sendFailedException.getMessage());
        }
        return ET;
    }

}

