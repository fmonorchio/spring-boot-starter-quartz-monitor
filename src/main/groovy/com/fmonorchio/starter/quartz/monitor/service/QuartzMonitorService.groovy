package com.fmonorchio.starter.quartz.monitor.service

import com.fmonorchio.starter.quartz.monitor.model.JobInfo
import org.quartz.JobKey

interface QuartzMonitorService {

    List<JobInfo> getJobDetails()
    List<JobInfo> getJobDetailsByGroup(String group)
    List<String> getJobGroups();

    JobInfo getJobDetail(JobKey key)

    void deleteJob(JobKey key)

    void triggerJob(JobKey key)

    void pauseJob(JobKey key)

    void interruptJob(JobKey key)

}
