package com.fmonorchio.starter.quartz.monitor.util.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

class DateSerializer extends StdSerializer<Date> {

    DateSerializer() {
        this(Date)
    }

    DateSerializer(Class<Date> dateClass) {
        super(dateClass)
    }

    @Override
    void serialize(Date value, JsonGenerator gen, SerializerProvider provider) {

        gen.with {
            writeStartObject()

            writeNumberField('timestamp', value.time)
            writeStringField('text', '...') //TODO example: 2018-10-21T15:39:34.369+0000

            writeEndObject()
        }
    }

}