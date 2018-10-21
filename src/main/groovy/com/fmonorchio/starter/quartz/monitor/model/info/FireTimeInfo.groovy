package com.fmonorchio.starter.quartz.monitor.model.info

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import groovy.transform.builder.Builder
import org.quartz.Trigger

@JsonPropertyOrder(['start', 'end', 'previous', 'next'])
class FireTimeInfo {

    @JsonIgnore
    Trigger trigger

    @Builder
    FireTimeInfo(Trigger trigger) {
        this.trigger = trigger
    }

    Date getStart() {
        trigger.startTime
    }

    Date getEnd() {
        trigger.endTime
    }

    Date getPrevious() {
        trigger.previousFireTime
    }

    Date getNext() {
        trigger.nextFireTime
    }

}