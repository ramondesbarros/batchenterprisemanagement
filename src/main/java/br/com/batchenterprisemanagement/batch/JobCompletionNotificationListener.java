package br.com.batchenterprisemanagement.batch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void afterJob(JobExecution jobExecution) {
//        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//            LOGGER.info("!!! JOB FINISHED! Time to verify the results");
//
//            String query = "SELECT brand, origin, characteristics FROM coffee";
//            jdbcTemplate.query(query, (rs, row) -> new Coffee(rs.getString(1), rs.getString(2), rs.getString(3)))
//                .forEach(coffee -> LOGGER.info("Found < {} > in the database.", coffee));
//        }
    
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
	        List<People> result = jdbcTemplate.query("SELECT firstname, lastname FROM people", 
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
