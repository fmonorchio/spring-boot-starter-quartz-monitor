package com.fmonorchio.starter.quartz.monitor.util.json

import com.fasterxml.jackson.core.JsonGenerator

import java.time.LocalDateTime

import static java.time.ZoneOffset.UTC
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

class DateSerializer extends AbstractSerializer<Date> {

    DateSerializer() {
        super(Date)
    }

    @Override
    void serialize(Date value, JsonGenerator gen) {

        def date = LocalDateTime.ofInstant(value.toInstant(), UTC)

        gen.with {
            writeNumberField('timestamp', value.time)
            writeStringField('text', ISO_LOCAL_DATE_TIME.format(date))
        }
    }

}