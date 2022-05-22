package com.springboot.crud.springbatch.confic;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

import com.springboot.crud.model.Customer;

public class CustomerBatchConfiguration {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public FlatFileItemReader<Customer> readFromCsv(){
		FlatFileItemReader<Customer> reader=new FlatFileItemReader<Customer>();
		reader.setResource(new FileSystemResource("E:\\dataSource2.csv"));
		//reader.setResource(new ClassPathResource("csv_input.csv"));
		reader.setLineMapper(new DefaultLineMapper<Customer>() {
			{
				setLineTokenizer (new DelimitedLineTokenizer() {
					{
						setNames(Customer.fields());
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {
					{
						setTargetType(Customer.class);
					}
				});
			}
			
		});
		return reader;
	}
	@Bean
	public JdbcBatchItemWriter<Customer>writerIntoDB(){
		JdbcBatchItemWriter<Customer> writer=new JdbcBatchItemWriter<Customer>();
		writer.setDataSource(dataSource);
		writer.setSql("insert into customer (customerid,trxamoun,description,trxdate,trxtime,accountnum) values(customerid,:trxamoun,:description,:trxdate,:trxtime,:accountnum)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Customer>());
		return writer;
	}
	@Bean
	public Step step() {
		return stepBuilderFactory.get("step").<Customer,Customer>chunk(10).reader(readFromCsv()).writer(writerIntoDB()).build();
	}
	@Bean
	public Job job() {
		return (Job) jobBuilderFactory.get("job").flow(step()).end().build();
	}

}
