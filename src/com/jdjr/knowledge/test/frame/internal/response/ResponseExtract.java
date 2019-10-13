package com.jdjr.knowledge.test.frame.internal.response;

public interface ResponseExtract<T extends ResponseExtract<T>> {
    T variable(String va1, String var2);
}
