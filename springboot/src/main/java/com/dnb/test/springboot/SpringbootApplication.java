package com.dnb.test.springboot;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@ComponentScan
@EnableBatchProcessing
@EnableScheduling
public class SpringbootApplication {

	@Autowired
    JobLauncher jobLauncher;
 
    @Autowired
    Job processJob;
    
    @Value("${csvFilepath}")
  	private String csvFilepath;
	
    public static void main(String[] args) {
    	SpringApplication.run(SpringbootApplication.class, args);
    	
	}
    public String getSpace(){
    	return new ServerDetails().getSpace();
     }
    
    @Scheduled(cron = "${batch.scheduler.cron}") /* (0 0 1 * * )*/
    public void filesClear() throws Exception {
		
    	System.out.println("in files clear");
		
		String space=getSpace();
		System.out.println("space :"+space);
    	if(space.equalsIgnoreCase("FULL")){
    		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).toJobParameters();
       		jobLauncher.run(processJob, jobParameters);
    	}
            
    }
    
}
