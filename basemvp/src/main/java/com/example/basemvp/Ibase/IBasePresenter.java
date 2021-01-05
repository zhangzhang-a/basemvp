package com.example.basemvp.Ibase;

public interface IBasePresenter<V extends IBaseView> {
    void attachView(V view);

    void unAttachView();
}
