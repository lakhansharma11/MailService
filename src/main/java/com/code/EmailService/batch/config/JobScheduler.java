package com.code.EmailService.batch.config;

import javax.batch.operations.JobRestartException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
@EnableScheduling
public class JobScheduler {
	
	
	  @Autowired 
	  private JobLauncher jobLauncher;
	 
	  @Autowired
	  BatchConfiguration batchConfiguration;
    @Autowired
    private Job job;

    @Scheduled(cron="*/20 * * * * *")
	public  void jobScheduler() throws Exception{
    	JobParameters jobParameters = new JobParametersBuilder().
    			addLong("time", System.currentTimeMillis()).toJobParameters();
    	
		
		  jobLauncher.run(batchConfiguration.emailSenderJob(), jobParameters);
		  //log.info(jobLauncher.toString()); 
		 // log.info(batchConfiguration.emailSenderJob().toString(), jobParameters);
		 
    	
    }
    
    

}


