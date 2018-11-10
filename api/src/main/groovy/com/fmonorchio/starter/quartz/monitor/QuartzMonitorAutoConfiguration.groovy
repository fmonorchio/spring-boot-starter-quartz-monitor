package com.fmonorchio.starter.quartz.monitor

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.module.SimpleModule
import com.fmonorchio.starter.quartz.monitor.controller.QuartzMonitorController
import com.fmonorchio.starter.quartz.monitor.converter.StringToJobKeyConverter
import com.fmonorchio.starter.quartz.monitor.service.QuartzMonitorService
import com.fmonorchio.starter.quartz.monitor.service.QuartzMonitorServiceImpl
import com.fmonorchio.starter.quartz.monitor.util.InfoAggregator
import com.fmonorchio.starter.quartz.monitor.util.json.DateSerializer
import org.quartz.Scheduler
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.AutoConfigureAfter
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@EnableCaching
@Configuration
@ConditionalOnBean([
        Scheduler,
        ObjectMapper
])
@AutoConfigureAfter([
        QuartzAutoConfiguration,
        JacksonAutoConfiguration
])
@ComponentScan('org.springframework.boot.autoconfigure')
@EnableConfigurationProperties(QuartzMonitorProperties)
@ConditionalOnProperty(value = 'spring.quartz.monitor.enabled', havingValue = 'true', matchIfMissing = true)
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

    @Bean
    StringToJobKeyConverter quartzMonitorStringJobKeyConverter() {
        return new StringToJobKeyConverter()
    }

    @Bean
    InfoAggregator quartzMonitorInfoAggregator(Scheduler scheduler) {
        return new InfoAggregator(scheduler)
    }

    @Bean
    QuartzMonitorService quartzMonitorService(Scheduler scheduler, InfoAggregator aggregator) {
        return new QuartzMonitorServiceImpl(scheduler, aggregator)
    }

    @Bean
    QuartzMonitorController quartzMonitorController(QuartzMonitorService service) {
        return new QuartzMonitorController(service)
    }

}