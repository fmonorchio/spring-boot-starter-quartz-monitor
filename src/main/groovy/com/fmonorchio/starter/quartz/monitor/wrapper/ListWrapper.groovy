package com.fmonorchio.starter.quartz.monitor.wrapper

class ListWrapper<T> extends Wrapper {

    List<T> content
    int size

    ListWrapper(List<T> content) {
        this.content = content
        this.size = content.size()
    }

}