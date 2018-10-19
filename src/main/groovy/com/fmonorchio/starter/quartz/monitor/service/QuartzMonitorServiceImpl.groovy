package com.fmonorchio.starter.quartz.monitor.service

import com.fmonorchio.starter.quartz.monitor.InfoAggregator
import com.fmonorchio.starter.quartz.monitor.model.JobInfo
import org.quartz.JobDetail
import org.quartz.JobKey
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuartzMonitorServiceImpl implements QuartzMonitorService {

    @Autowired
    Scheduler scheduler

    @Autowired
    InfoAggregator aggregator

    @Override
    List<JobInfo> getJobDetails() {

        def matcher = GroupMatcher.anyJobGroup()
        scheduler.getJobKeys(matcher).collect { key ->
            aggregator.aggregateBy(key)
        }
    }

    @Override
    List<JobInfo> getJobDetailsByGroup(String group) {

        def matcher = GroupMatcher.jobGroupEquals(group)
        scheduler.getJobKeys(matcher).collect { key ->
            aggregator.aggregateBy(key)
        }
    }

    @Override
    JobInfo getJobDetail(JobKey key) {
        aggregator.aggregateBy(key)
    }

    @Override
    void deleteJob(JobKey key) {
        scheduler.deleteJob(key)
    }

    @Override
    void triggerJob(JobKey key) {
        scheduler.triggerJob(key)
    }

    @Override
    void pauseJob(JobKey key) {
        scheduler.pauseJob(key)
    }

    @Override
    void interruptJob(JobKey key) {
        scheduler.interrupt(key)
    }

    @Override
    List<String> getJobGroups() {
        scheduler.getJobGroupNames()
    }

}