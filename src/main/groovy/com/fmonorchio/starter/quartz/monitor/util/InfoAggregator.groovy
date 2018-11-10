package com.fmonorchio.starter.quartz.monitor.util

import com.fmonorchio.starter.quartz.monitor.exception.NotFoundException
import com.fmonorchio.starter.quartz.monitor.model.JobData
import com.fmonorchio.starter.quartz.monitor.model.info.JobInfo
import com.fmonorchio.starter.quartz.monitor.model.info.TriggerInfo
import groovy.transform.TupleConstructor
import org.quartz.JobKey
import org.quartz.Scheduler

@TupleConstructor
class InfoAggregator {

    Scheduler scheduler

    JobData aggregateBy(JobKey key) {

        def detail = Optional.ofNullable(scheduler.getJobDetail(key))
                .orElseThrow { new NotFoundException() }

        def job = new JobInfo(detail)
        def triggers = scheduler.getTriggersOfJob(key).collect { new TriggerInfo(it) }

        return JobData.builder()
                .job(job)
                .triggers(triggers)
                .build()
    }

}
