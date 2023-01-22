package com.code.EmailService.batch.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.code.EmailService.entity.EmailTask;

public class EmailMapper implements RowMapper<EmailTask> {
    @Override
    public EmailTask mapRow(ResultSet rs, int rowNum) throws SQLException {
        
    	EmailTask et = new EmailTask();
        	et.setID(rs.getLong("ID"));
        	et.setCREATED(rs.getDate("CREATED"));
        	et.setCREATED_BY(rs.getString("CREATED_BY"));
        	et.setCustomer_MailID(rs.getString("Customer_MailID"));
        	et.setSTATUS(rs.getLong("STATUS"));
        	et.setEmail_SENT_TIMESTAMP(rs.getDate("Email_SENT_TIMESTAMP"));
        	et.setEMAIL_ERROR_IF_ANY(rs.getString("EMAIL_ERROR_IF_ANY"));
        	et.setEMAIL_CONTENT(rs.getString("EMAIL_CONTENT"));
        		return et;
    }
}