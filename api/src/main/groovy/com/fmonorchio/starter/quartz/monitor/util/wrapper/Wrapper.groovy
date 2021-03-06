package com.fmonorchio.starter.quartz.monitor.util.wrapper

class Wrapper {

    static <T> Wrapper wrap(T data) {

        if (data instanceof List) {
            return new ListWrapper<>(data)
        }
        return new ScalarWrapper<>(data)
    }

}