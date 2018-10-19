package com.fmonorchio.starter.quartz.monitor

import com.fmonorchio.starter.quartz.monitor.model.JobInfo
import org.quartz.JobKey
import org.quartz.Scheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InfoAggregator {

    @Autowired
    Scheduler scheduler

    JobInfo aggregateBy(JobKey key) {

        def detail = scheduler.getJobDetail(key)
        def triggers = scheduler.getTriggersOfJob(key)
        JobInfo.builder()
                .jobDetail(detail)
                .triggers(triggers)
                .build()
    }

}
