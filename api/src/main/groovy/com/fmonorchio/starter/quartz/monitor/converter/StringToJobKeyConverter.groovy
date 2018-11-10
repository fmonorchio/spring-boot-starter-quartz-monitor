package com.fmonorchio.starter.quartz.monitor.converter

import com.fmonorchio.starter.quartz.monitor.exception.KeyFormatException
import org.quartz.JobKey
import org.springframework.core.convert.converter.Converter

class StringToJobKeyConverter implements Converter<String, JobKey> {

    @Override
    JobKey convert(String source) {

        def matcher = (source =~ /(?<group>\w+)\.(?<name>[\w-]+)/)
        if (matcher.matches()) {
            return new JobKey(matcher.group('name'), matcher.group('group'))
        }

        throw new KeyFormatException()
    }

}