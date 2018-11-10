package com.fmonorchio.starter.quartz.monitor.service

import com.fmonorchio.starter.quartz.monitor.model.JobData
import org.quartz.JobKey

interface QuartzMonitorService {

    List<JobData> getJobData()
    List<JobData> getJobDataByGroup(String group)

    JobData getJobData(JobKey key)

    List<String> getJobGroups();

    void deleteJob(JobKey key)

    void triggerJob(JobKey key)

    void pauseJob(JobKey key)
    void resumeJob(JobKey key)

    void interruptJob(JobKey key)

}
