package com.fmonorchio.starter.quartz.monitor


import org.quartz.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/*
 * TODO: Remove after development cycle.
 */

@SpringBootApplication
class QuartzMonitorApplication {

    @Autowired
    Scheduler scheduler

    static class BounceMessageJob implements Job {

        @Override
        void execute(JobExecutionContext context) {
            println 'Message printed each 5 seconds.'
        }

    }

    static class OneShotMessageJob implements Job {

        @Override
        void execute(JobExecutionContext context) {
            println 'Message printed one time. Trigger manually if needed.'
        }

    }

    static void main(String[] args) {
        SpringApplication.run(QuartzMonitorApplication, args)
    }

    @Autowired
    void configure() {
        def bounceMessageSchedule = SimpleScheduleBuilder.repeatSecondlyForever(5)
        def oneShotMessageSchedule = SimpleScheduleBuilder.simpleSchedule()

        scheduler.scheduleJob(
                JobBuilder.newJob(BounceMessageJob)
                        .withIdentity('bounce-message-job')
                        .build(),
                TriggerBuilder.newTrigger()
                        .withSchedule(bounceMessageSchedule)
                        .build())
        scheduler.scheduleJob(
                JobBuilder.newJob(OneShotMessageJob)
                        .withIdentity('one-shot-message-job')
                        .build(),
                TriggerBuilder.newTrigger()
                        .withSchedule(oneShotMessageSchedule)
                        .build())
    }

}