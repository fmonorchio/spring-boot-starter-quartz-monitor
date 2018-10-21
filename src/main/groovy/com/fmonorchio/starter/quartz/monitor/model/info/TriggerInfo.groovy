package com.fmonorchio.starter.quartz.monitor.model.info

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import groovy.transform.builder.Builder
import org.quartz.Trigger
import org.quartz.TriggerKey

@JsonPropertyOrder(['key', 'description', 'priority', 'fires'])
class TriggerInfo {

    @JsonIgnore
    Trigger trigger

    @Builder
    TriggerInfo(Trigger trigger) {
        this.trigger = trigger
    }

    TriggerKey getKey() {
        trigger.key
    }

    String getDescription() {
        trigger.description
    }

    int getPriority() {
        trigger.priority
    }

    FireTimeInfo getFires() {
        FireTimeInfo.builder()
                .trigger(trigger)
                .build()
    }

}