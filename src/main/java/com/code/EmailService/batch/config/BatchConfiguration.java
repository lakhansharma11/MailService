package com.code.EmailService.batch.config;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.sql.DataSource;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.code.EmailService.batch.mapper.EmailMapper;
import com.code.EmailService.batch.processor.EmailProcessor;
import com.code.EmailService.batch.writer.EmailWriter;
import com.code.EmailService.entity.EmailTask;

import lombok.extern.slf4j.Slf4j;



@EnableBatchProcessing
@Slf4j
@Configuration
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    
    private final String JOB_NAME = "emailSenderJob";
    private final String STEP_NAME = "emailSenderStep";

    Random random = new Random();
    int randomWithNextInt = random.nextInt();


    @Bean(name = "emailSenderJob")
    public Job emailSenderJob() {
        return this.jobBuilderFactory.get(JOB_NAME+randomWithNextInt)
                .start(emailSenderStep())
                .build();
    }

    @Bean
    public Step emailSenderStep() {
        return this.stepBuilderFactory
                .get(STEP_NAME)
                .<EmailTask, EmailTask>chunk(100)
                .reader(activeOrderReader())
                .processor(orderItemProcessor())
                .writer(orderWriter())
                .build();
    }

    @Bean
    public ItemProcessor<EmailTask, EmailTask> orderItemProcessor() {
        return new EmailProcessor();
    }

    @Bean
    public ItemWriter<EmailTask> orderWriter() {
        return new EmailWriter();
    }

    @Bean
    public ItemReader<EmailTask> activeOrderReader() {
        String sql = "SELECT * FROM EMAIL_NOTIFICATION_TASK WHERE STATUS=0";
        
        //JdbcCursorItemReaderBuilder<EmailTask> CursorItemReader =new  JdbcCursorItemReaderBuilder<>();
        //CursorItemReader.dataSource(dataSource);
        log.info(sql.toLowerCase());
        
        return new JdbcCursorItemReaderBuilder<EmailTask>()
                .name("activeOrderReader")
                .dataSource(dataSource)
                .sql(sql)
                .rowMapper(new EmailMapper())
                .build();
    }
}
