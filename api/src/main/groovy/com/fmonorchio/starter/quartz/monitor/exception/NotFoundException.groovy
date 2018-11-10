package com.fmonorchio.starter.quartz.monitor.exception

class NotFoundException extends RuntimeException {

    NotFoundException() {
        super('Resource not found')
    }

}