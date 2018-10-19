package com.fmonorchio.starter.quartz.monitor

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS

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

        mapper.configure(FAIL_ON_EMPTY_BEANS, false)
        mapper.setSerializationInclusion(NON_NULL)
    }

}