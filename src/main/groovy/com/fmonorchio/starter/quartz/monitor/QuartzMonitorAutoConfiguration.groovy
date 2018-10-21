package com.fmonorchio.starter.quartz.monitor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fmonorchio.starter.quartz.monitor.util.json.DateSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@AutoConfigureAfter([
        QuartzAutoConfiguration,
        JacksonAutoConfiguration
])
@ComponentScan('com.fmonorchio.starter.quartz.monitor')
@EnableConfigurationProperties(QuartzMonitorProperties)
@ConditionalOnProperty(prefix = 'spring.quartz.monitor', value = 'enabled', havingValue = 'true', matchIfMissing = true)
class QuartzMonitorAutoConfiguration {

    @Autowired
    QuartzMonitorProperties properties

    @Autowired
    @SuppressWarnings('GrMethodMayBeStatic')
    void configure(ObjectMapper mapper) {

        def module = new SimpleModule()
        module.addSerializer(Date, new DateSerializer())

        mapper.registerModule(module)
    }

}