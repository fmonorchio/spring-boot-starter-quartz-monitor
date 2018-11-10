package com.fmonorchio.starter.quartz.monitor.test

import org.quartz.JobBuilder
import org.quartz.Scheduler
import org.quartz.SimpleScheduleBuilder
import org.quartz.TriggerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class TestConfiguration {

    @Autowired
    private Scheduler scheduler

    @Autowired
    void configure() {

        scheduler.scheduleJob(JobBuilder.newJob(TestJob.class)
                .withIdentity('test-job-5sec', 'default')
                .usingJobData('interval', '5')
                .usingJobData('type', 'seconds')
                .build(), TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .build())
        scheduler.scheduleJob(JobBuilder.newJob(TestJob.class)
                .withIdentity('test-job-1min', 'default')
                .usingJobData('interval', '1')
                .usingJobData('type', 'minute')
                .build(), TriggerBuilder.newTrigger()
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever())
                .build())
    }
}