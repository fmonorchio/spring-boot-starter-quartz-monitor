package com.fmonorchio.starter.quartz.monitor.service

import com.fmonorchio.starter.quartz.monitor.model.JobData
import org.quartz.JobKey

interface QuartzMonitorService {

    List<JobData> getJobDetails()
    List<JobData> getJobDetailsByGroup(String group)
    List<String> getJobGroups();

    JobData getJobDetail(JobKey key)

    void deleteJob(JobKey key)

    void triggerJob(JobKey key)

    void pauseJob(JobKey key)

    void interruptJob(JobKey key)

}
