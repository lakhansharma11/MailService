package com.code.EmailService.batch.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import com.code.EmailService.Repository.EmailRepository;
import com.code.EmailService.entity.EmailTask;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmailWriter  implements ItemWriter<EmailTask> {
	
    @Autowired
    EmailRepository emailRepository;

    @Override
    public void write(List<? extends EmailTask> list) throws Exception {
      log.info("item writer: {}", list.get(0));
      emailRepository.saveAllAndFlush(list);
    }

	
}