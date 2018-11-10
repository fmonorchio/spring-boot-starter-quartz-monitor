package com.fmonorchio.starter.quartz.monitor.test

import groovy.util.logging.Slf4j
import org.quartz.Job
import org.quartz.JobExecutionContext

@Slf4j
class TestJob implements Job {

    @Override
    void execute(JobExecutionContext context) {
        def data = context.mergedJobDataMap
        log.info("Test message printed each ${data['interval']} ${data['type']}")
    }

}