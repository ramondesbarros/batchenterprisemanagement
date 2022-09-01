package br.com.batchenterprisemanagement.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener implements JobExecutionListener {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Override
	public void beforeJob(JobExecution jobExecution) {	
		
		System.out.println("Executing job id " + jobExecution.getId());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	        List<People> result = jdbcTemplate.query("SELECT firstname, lastname, city, age FROM people", 
	        		new RowMapper<People>() {
	            @Override
	            public People mapRow(ResultSet rs, int row) throws SQLException {
	                return new People(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
	            }
	        });
	        System.out.println("Number of Records:"+result.size());
		}
		
	}

}
