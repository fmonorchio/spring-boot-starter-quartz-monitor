package com.fmonorchio.starter.quartz.monitor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spring.quartz.monitor")
public class QuartzMonitorProperties {

	Boolean enabled;

}