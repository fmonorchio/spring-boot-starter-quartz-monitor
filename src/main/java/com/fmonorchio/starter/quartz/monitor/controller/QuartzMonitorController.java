package com.fmonorchio.starter.quartz.monitor.controller;

import com.fmonorchio.starter.quartz.monitor.service.QuartzMonitorService;
import lombok.Data;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RestController
@RequestMapping("quartz/monitor/scheduled-jobs")
public class QuartzMonitorController
{

	@Autowired
	private QuartzMonitorService quartzMonitorService;

	@GetMapping
	public ResponseEntity<List<JobDetail>> getQuartzJobs()
	{
		return ResponseEntity.ok(quartzMonitorService.getQuartzJobs());
	}

	@GetMapping("{keyName}")
	public ResponseEntity<JobDetail> getQuartzJob(@PathVariable String keyName)
	{
		return ResponseEntity.ok(quartzMonitorService.getQuartzJobByKey(keyName));
	}

	@DeleteMapping("{keyName}")
	public ResponseEntity<Boolean> deleteQuartzJob(@PathVariable String keyName)
	{
		return ResponseEntity.ok(quartzMonitorService.deleteQuartzJobByKey(keyName));
	}

	@PostMapping("{keyName}/start")
	public ResponseEntity<Void> startQuartzJob(@PathVariable String keyName)
	{
		quartzMonitorService.startQuartzJobByKey(keyName);
		return ResponseEntity.ok()
				.build();
	}

	@PostMapping("{keyName}/pause")
	public ResponseEntity<Void> pauseQuartzJob(@PathVariable String keyName)
	{
		quartzMonitorService.pauseQuartzJobByKey(keyName);
		return ResponseEntity.ok()
				.build();
	}
}