package com.fmonorchio.starter.quartz.monitor;

import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Data
@SpringBootApplication
public class QuartzMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzMonitorApplication.class, args);
    }

}