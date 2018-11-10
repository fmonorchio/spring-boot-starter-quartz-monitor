package com.fmonorchio.starter.quartz.monitor.controller

import com.fmonorchio.starter.quartz.monitor.service.QuartzMonitorService
import com.fmonorchio.starter.quartz.monitor.wrapper.Wrapper
import groovy.transform.TupleConstructor
import org.quartz.JobKey
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static com.fmonorchio.starter.quartz.monitor.wrapper.Wrapper.wrap
import static org.springframework.http.ResponseEntity.ok

@TupleConstructor
@SuppressWarnings('unused')
@RequestMapping('quartz-monitor')
class QuartzMonitorController {

    QuartzMonitorService quartzMonitorService

    @GetMapping('scheduled-jobs')
    ResponseEntity<Wrapper> getJobDetails(@RequestParam(required = false) String group) {

        def data
        if (group) {
            data = wrap(quartzMonitorService.getJobDataByGroup(group))
        } else {
            data = wrap(quartzMonitorService.getJobData())
        }

        return ok(data)
    }

    @GetMapping('scheduled-jobs/groups')
    ResponseEntity<Wrapper> getJobGroups() {

        def data = wrap(quartzMonitorService.getJobGroups())
        return ok(data)
    }

    @GetMapping('scheduled-jobs/{key}')
    ResponseEntity<Wrapper> getJobData(@PathVariable JobKey key) {

        def data = wrap(quartzMonitorService.getJobData(key))
        return ok(data)
    }

    @DeleteMapping('scheduled-jobs/{key}')
    ResponseEntity<Boolean> deleteJob(@PathVariable JobKey key) {

        quartzMonitorService.deleteJob(key)
        return ok().build()
    }

    @PostMapping('scheduled-jobs/{key}/trigger')
    ResponseEntity<Void> triggerJob(@PathVariable JobKey key) {

        quartzMonitorService.triggerJob(key)
        return ok().build()
    }

    @PostMapping('scheduled-jobs/{key}/pause')
    ResponseEntity<Void> pauseJob(@PathVariable JobKey key) {

        quartzMonitorService.pauseJob(key)
        return ok().build()
    }

    @PostMapping('scheduled-jobs/{key}/resume')
    ResponseEntity<Void> resumeJob(@PathVariable JobKey key) {

        quartzMonitorService.resumeJob(key)
        return ok().build()
    }

    @PostMapping('scheduled-jobs/{key}/interrupt')
    ResponseEntity<Void> interruptJob(@PathVariable JobKey key) {

        quartzMonitorService.interruptJob(key)
        return ok().build()
    }

}