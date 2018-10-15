package com.fmonorchio.starter.quartz.monitor.service;

import lombok.Data;
import lombok.SneakyThrows;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.quartz.JobKey.jobKey;

@Data
@Service
public class QuartzMonitorServiceImpl implements QuartzMonitorService
{

	@Autowired
	private Scheduler scheduler;

	@Override
	@SneakyThrows
	public List<JobDetail> getQuartzJobs()
	{
		List<JobDetail> jobs = new LinkedList<>();

		Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.anyGroup());
		for (JobKey key : keys) {
			jobs.add(scheduler.getJobDetail(key));
		}
		return jobs;
	}

	@Override
	@SneakyThrows
	public JobDetail getQuartzJobByKey(String keyName)
	{
		return scheduler.getJobDetail(jobKey(keyName));
	}

	@Override
	@SneakyThrows
	public Boolean deleteQuartzJobByKey(String keyName)
	{
		return scheduler.deleteJob(jobKey(keyName));
	}

	@Override
	@SneakyThrows
	public void startQuartzJobByKey(String keyName)
	{
		scheduler.triggerJob(jobKey(keyName));
	}

	@Override
	@SneakyThrows
	public void pauseQuartzJobByKey(String keyName)
	{
		scheduler.pauseJob(jobKey(keyName));
	}
}