package com.code.EmailService.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code.EmailService.entity.EmailTask;


@Repository
public interface EmailRepository extends JpaRepository<EmailTask, Long> {
    
	List<EmailTask> findBySTATUS(Long STATUS); 
	 

}
