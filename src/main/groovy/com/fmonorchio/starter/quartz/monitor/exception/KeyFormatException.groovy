package com.fmonorchio.starter.quartz.monitor.exception

class KeyFormatException extends RuntimeException {

    KeyFormatException() {
        super('Invalid key')
    }

}