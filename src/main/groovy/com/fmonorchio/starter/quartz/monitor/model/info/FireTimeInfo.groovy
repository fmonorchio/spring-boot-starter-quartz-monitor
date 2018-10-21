package com.fmonorchio.starter.quartz.monitor.model.info

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.quartz.Trigger

@SuppressWarnings('unused')
@JsonPropertyOrder(['start', 'end', 'previous', 'next'])
class FireTimeInfo {

    @JsonIgnore
    Trigger trigger

    FireTimeInfo(Trigger trigger) {
        this.trigger = trigger
    }

    Date getStart() {
        return trigger.startTime
    }

    Date getEnd() {
        return trigger.endTime
    }

    Date getPrevious() {
        return trigger.previousFireTime
    }

    Date getNext() {
        return trigger.nextFireTime
    }

}