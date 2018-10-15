package com.fmonorchio.starter.quartz.monitor;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ComponentScan("com.fmonorchio.starter.quartz.monitor")
@AutoConfigureAfter(QuartzAutoConfiguration.class)
@EnableConfigurationProperties(QuartzMonitorProperties.class)
@ConditionalOnProperty(prefix = "spring.quartz.monitor", value = "enabled", havingValue = "true", matchIfMissing = true)
class QuartzMonitorAutoConfiguration
{

	@Autowired
	QuartzMonitorProperties properties;
}