package com.jdjr.knowledge.test.frame.internal.entity;

public class DataEntity<T> {
    private T data;

    public DataEntity() {
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
