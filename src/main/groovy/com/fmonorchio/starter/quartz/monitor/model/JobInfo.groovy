package com.fmonorchio.starter.quartz.monitor.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import groovy.transform.builder.Builder
import org.quartz.JobDetail
import org.quartz.Trigger

@Builder
class JobInfo {

    @JsonIgnoreProperties(['name', 'group', 'fullName', 'jobBuilder'])
    JobDetail jobDetail

    @JsonIgnoreProperties([
            'name', 'group', 'jobKey', 'jobName', 'jobGroup', 'fullName',
            'jobDataMap', 'fullJobName', 'triggerBuilder', 'scheduleBuilder'])
    List<Trigger> triggers

}