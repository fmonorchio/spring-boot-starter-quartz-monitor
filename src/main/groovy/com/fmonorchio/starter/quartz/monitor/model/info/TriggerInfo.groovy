package com.fmonorchio.starter.quartz.monitor.model.info

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.quartz.Trigger
import org.quartz.TriggerKey

@SuppressWarnings('unused')
@JsonPropertyOrder(['key', 'description', 'class', 'priority', 'fires'])
class TriggerInfo {

    @JsonIgnore
    Trigger trigger

    TriggerInfo(Trigger trigger) {
        this.trigger = trigger
    }

    @JsonProperty('class')
    Class<? extends Trigger> getClassName() {
        return trigger.class
    }

    TriggerKey getKey() {
        return trigger.key
    }

    String getDescription() {
        return trigger.description
    }

    int getPriority() {
        return trigger.priority
    }

    FireTimeInfo getFires() {
        return new FireTimeInfo(trigger)
    }

}