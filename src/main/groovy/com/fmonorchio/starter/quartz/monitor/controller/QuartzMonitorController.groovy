package com.fmonorchio.starter.quartz.monitor.controller

import com.fmonorchio.starter.quartz.monitor.model.JobInfo
import com.fmonorchio.starter.quartz.monitor.service.QuartzMonitorService
import org.quartz.JobKey
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static org.springframework.http.ResponseEntity.ok

@RestController
@RequestMapping
@SuppressWarnings('unused')
class QuartzMonitorController {

    @Autowired
    QuartzMonitorService quartzMonitorService

    @GetMapping('quartz/scheduled-jobs')
    ResponseEntity<List<JobInfo>> getJobDetails(@RequestParam(required = false) String group) {

        if (group) {
            return ok(quartzMonitorService.getJobDetailsByGroup(group))
        }
        ok(quartzMonitorService.getJobDetails())
    }

    @GetMapping('quartz/scheduled-jobs/groups')
    ResponseEntity<List<String>> getJobGroups() {

        ok(quartzMonitorService.getJobGroups())
    }

    @GetMapping('quartz/scheduled-jobs/{key}')
    ResponseEntity<JobInfo> getQuartzJob(@PathVariable JobKey key) {

        ok(quartzMonitorService.getJobDetail(key))
    }

    @DeleteMapping('quartz/scheduled-jobs/{key}')
    ResponseEntity<Boolean> deleteJob(@PathVariable JobKey key) {

        quartzMonitorService.deleteJob(key)
        ok().build()
    }

    @PostMapping('quartz/scheduled-jobs/{key}/trigger')
    ResponseEntity<Void> triggerJob(@PathVariable JobKey key) {

        quartzMonitorService.triggerJob(key)
        ok().build()
    }

    @PostMapping('quartz/scheduled-jobs/{key}/pause')
    ResponseEntity<Void> pauseJob(@PathVariable JobKey key) {

        quartzMonitorService.pauseJob(key)
        ok().build()
    }

    @PostMapping('quartz/scheduled-jobs/{key}/interrupt')
    ResponseEntity<Void> interruptJob(@PathVariable JobKey key) {

        quartzMonitorService.interruptJob(key)
        ok().build()
    }

}