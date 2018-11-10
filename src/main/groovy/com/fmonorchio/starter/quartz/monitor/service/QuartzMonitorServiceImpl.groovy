package com.fmonorchio.starter.quartz.monitor.service

import com.fmonorchio.starter.quartz.monitor.model.JobData
import com.fmonorchio.starter.quartz.monitor.util.InfoAggregator
import groovy.transform.TupleConstructor
import org.quartz.JobKey
import org.quartz.Scheduler
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable

@TupleConstructor
class QuartzMonitorServiceImpl implements QuartzMonitorService {

    Scheduler scheduler

    InfoAggregator aggregator

    @Override
    @Cacheable('getJobs')
    List<JobData> getJobData() {

        def matcher = GroupMatcher.anyJobGroup()
        return scheduler.getJobKeys(matcher).collect { key ->
            aggregator.aggregateBy(key)
        }
    }

    @Override
    @Cacheable(value = 'getJobsByGroup', key = '#group')
    List<JobData> getJobDataByGroup(String group) {

        def matcher = GroupMatcher.jobGroupEquals(group)
        return scheduler.getJobKeys(matcher).collect { key ->
            aggregator.aggregateBy(key)
        }
    }

    @Override
    @Cacheable(value = 'getJob', key = '#key')
    JobData getJobData(JobKey key) {
        return aggregator.aggregateBy(key)
    }

    @Override
    @Cacheable('getJobGroups')
    List<String> getJobGroups() {
        return scheduler.getJobGroupNames()
    }

    @Override
    @CacheEvict(['getJobs', 'getJobsByGroup', 'getJob', 'getJobGroups'])
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
    void resumeJob(JobKey key) {
        scheduler.resumeJob(key)
    }

    @Override
    void interruptJob(JobKey key) {
        scheduler.interrupt(key)
    }

}