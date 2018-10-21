package com.fmonorchio.starter.quartz.monitor.model.info

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import groovy.transform.builder.Builder
import org.quartz.Job
import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.quartz.JobKey

@JsonPropertyOrder(['key', 'description', 'class', 'data', 'durable'])
class JobInfo {

    @JsonIgnore
    JobDetail detail

    @Builder
    JobInfo(JobDetail detail) {
        this.detail = detail
    }

    @JsonProperty('class')
    Class<? extends Job> getClassName() {
        detail.jobClass
    }

    JobKey getKey() {
        detail.key
    }

    JobDataMap getData() {
        detail.jobDataMap
    }

    String getDescription() {
        detail.description
    }

    boolean isDurable() {
        detail.durable
    }

}