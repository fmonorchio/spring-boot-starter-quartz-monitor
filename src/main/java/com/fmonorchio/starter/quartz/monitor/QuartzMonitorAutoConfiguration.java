package com.fmonorchio.starter.quartz.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.Data;
import lombok.SneakyThrows;
import org.quartz.JobBuilder;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ComponentScan("com.fmonorchio.starter.quartz.monitor")
@AutoConfigureAfter({QuartzAutoConfiguration.class, JacksonAutoConfiguration.class })
@EnableConfigurationProperties(QuartzMonitorProperties.class)
@ConditionalOnProperty(prefix = "spring.quartz.monitor", value = "enabled", havingValue = "true", matchIfMissing = true)
public class QuartzMonitorAutoConfiguration
{

	@Autowired
	QuartzMonitorProperties properties;

	@Autowired
	private TestJob testJob;

	@Autowired
	private Scheduler scheduler;

	@Autowired
	@SneakyThrows
	public void configure(ObjectMapper mapper)
	{
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		scheduler.scheduleJob(JobBuilder.newJob(TestJob.class)
				.withIdentity("test-job")
				.build(), TriggerBuilder.newTrigger()
				.withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
				.build());
	}

	@Bean
	@ConditionalOnMissingBean(ObjectMapper.class)
	public ObjectMapper mapper()
	{
		return new ObjectMapper();
	}
}