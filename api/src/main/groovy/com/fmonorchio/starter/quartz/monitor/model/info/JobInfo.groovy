package com.fmonorchio.starter.quartz.monitor.model.info

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.quartz.Job
import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.quartz.JobKey

@SuppressWarnings('unused')
@JsonPropertyOrder(['key', 'description', 'class', 'data', 'durable'])
class JobInfo {

    @JsonIgnore
    JobDetail detail

    JobInfo(JobDetail detail) {
        this.detail = detail
    }

    @JsonProperty('class')
    Class<? extends Job> getClassName() {
        return detail.jobClass
    }

    JobKey getKey() {
        return detail.key
    }

    JobDataMap getData() {
        return detail.jobDataMap
    }

    String getDescription() {
        return detail.description
    }

    boolean isDurable() {
        return detail.durable
    }

}