package com.fmonorchio.starter.quartz.monitor.model

import com.fmonorchio.starter.quartz.monitor.model.info.JobInfo
import com.fmonorchio.starter.quartz.monitor.model.info.TriggerInfo
import groovy.transform.builder.Builder

@Builder
class JobData {

    JobInfo job

    List<TriggerInfo> triggers

}