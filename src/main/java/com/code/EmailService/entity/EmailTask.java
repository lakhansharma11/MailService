package com.code.EmailService.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@Builder
@Entity(name = "EMAIL_NOTIFICATION_TASK")
@NoArgsConstructor
@AllArgsConstructor
public class EmailTask {
	
	@Id
	private Long ID;
	private Date CREATED;
	private String CREATED_BY;
	private Long STATUS;
	private Date Email_SENT_TIMESTAMP;
	private String EMAIL_ERROR_IF_ANY;
	private String EMAIL_CONTENT;
	private String Customer_MailID;


}
