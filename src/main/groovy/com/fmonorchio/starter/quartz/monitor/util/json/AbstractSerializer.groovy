package com.fmonorchio.starter.quartz.monitor.util.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer

abstract class AbstractSerializer<T> extends StdSerializer<T> {

    AbstractSerializer(Class<T> c) {
        super(c)
    }

    abstract void serialize(T value, JsonGenerator generator)

    @Override
    final void serialize(T value, JsonGenerator gen, SerializerProvider provider) {
        gen.writeStartObject()
        this.serialize(value, gen)
        gen.writeEndObject()
    }

}