package com.fmonorchio.starter.quartz.monitor.util

import com.fmonorchio.starter.quartz.monitor.model.JobData
import com.fmonorchio.starter.quartz.monitor.model.info.JobInfo
import com.fmonorchio.starter.quartz.monitor.model.info.TriggerInfo
import org.quartz.JobKey
import org.quartz.Scheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InfoAggregator {

    @Autowired
    Scheduler scheduler

    JobData aggregateBy(JobKey key) {

        def detail = new JobInfo(scheduler.getJobDetail(key))
        def triggers = scheduler.getTriggersOfJob(key).collect { new TriggerInfo(it) }
        JobData.builder()
                .job(detail)
                .triggers(triggers)
                .build()
    }

}
