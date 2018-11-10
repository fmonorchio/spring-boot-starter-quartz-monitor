package com.fmonorchio.starter.quartz.monitor.controller

import com.fmonorchio.starter.quartz.monitor.model.JobData
import com.fmonorchio.starter.quartz.monitor.service.QuartzMonitorService
import org.quartz.JobKey
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static org.springframework.http.ResponseEntity.ok

@SuppressWarnings('unused')
@RequestMapping('quartz-monitor')
class QuartzMonitorController {

    @Autowired
    QuartzMonitorService quartzMonitorService

    @GetMapping('scheduled-jobs')
    ResponseEntity<List<JobData>> getJobDetails(@RequestParam(required = false) String group) {

        if (group) {
            return ok(quartzMonitorService.getJobDataByGroup(group))
        }
        return ok(quartzMonitorService.getJobData())
    }

    @GetMapping('scheduled-jobs/groups')
    ResponseEntity<List<String>> getJobGroups() {

        return ok(quartzMonitorService.getJobGroups())
    }

    @GetMapping('scheduled-jobs/{key}')
    ResponseEntity<JobData> getJobData(@PathVariable JobKey key) {

        return ok(quartzMonitorService.getJobData(key))
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