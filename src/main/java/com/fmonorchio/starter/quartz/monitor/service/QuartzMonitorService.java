package com.fmonorchio.starter.quartz.monitor.service;

import org.quartz.JobDetail;

import java.util.List;

public interface QuartzMonitorService
{

	List<JobDetail> getQuartzJobs();

	JobDetail getQuartzJobByKey(String keyName);

	Boolean deleteQuartzJobByKey(String keyName);

	void startQuartzJobByKey(String keyName);

	void pauseQuartzJobByKey(String keyName);

}
