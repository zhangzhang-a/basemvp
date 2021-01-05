package com.example.basemvp.Ibase;

import io.reactivex.disposables.Disposable;

public interface IBaseModel {
    void addDisposable(Disposable disposable);

    void clear();
}
