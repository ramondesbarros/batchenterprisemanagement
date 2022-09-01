package br.com.batchenterprisemanagement.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DefaultBatchConfigurer {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

//	@Value("${file.input}")
//	private String fileInput;

	@Bean
	public LineMapper<People> lineMapper() {

		DefaultLineMapper<People> lineMapper = new DefaultLineMapper<People>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer() {
			{
				setNames(new String[] { "firstname", "lastname", "city", "age" });
			}
		});
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<People>() {
			{
				setTargetType(People.class);
			}
		});
		return lineMapper;
	}

	@Bean
	public FlatFileItemReader<People> reader() {

//		return new FlatFileItemReaderBuilder<People>()
//				.name("peopleItemReader")
//				.resource(new ClassPathResource("fileenterprisemanagement.csv"))
//				.lineMapper(lineMapper())
//				.linesToSkip(1)
//				.build();
		
		return new FlatFileItemReaderBuilder<People>()
				.name("peopleItemReader")
				.resource(new ClassPathResource("fileenterprisemanagement.csv"))
				.delimited()
				.names(new String[]{ "firstname", "lastname", "city", "age" })
				  .fieldSetMapper(new BeanWrapperFieldSetMapper<People>() {{
					   setTargetType(People.class);
				  }})
				.linesToSkip(1)
				.build();

	}

	@Bean
	public PeopleItemProcessor processor() {
		return new PeopleItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<People> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<People>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<People>())
				.sql("INSERT INTO people (firstname, lastname, city, age) VALUES (:firstname, :lastname, :city, :age)")
				.dataSource(dataSource).build();
	}

	@Bean
	public Job importProfileJob(JobCompletionNotificationListener listener, Step step1) {
		return jobBuilderFactory
				.get("importProfileJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<People> writer) {
		return stepBuilderFactory
				.get("step1")
				.<People, People>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}

	@Bean
	public DataSource getDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/batchenterprisemanagement");
		dataSource.setUsername("dbase");
		dataSource.setPassword("5yst3M@xls");
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Override
	public void setDataSource(DataSource dataSource) {
	}
}
