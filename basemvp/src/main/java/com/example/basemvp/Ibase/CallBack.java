package com.example.basemvp.Ibase;

public interface CallBack<T> {
    void successData(T data);

    void failedData(String errorMsg);
}
