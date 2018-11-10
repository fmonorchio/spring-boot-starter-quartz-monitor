package com.fmonorchio.starter.quartz.monitor.wrapper

class ScalarWrapper<T> extends Wrapper {

    T content

    ScalarWrapper(T content) {
        this.content = content
    }

}