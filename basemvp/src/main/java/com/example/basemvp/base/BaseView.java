package com.example.basemvp.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.basemvp.Ibase.IBasePresenter;
import com.example.basemvp.Ibase.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseView<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    protected P presenter;
    Unbinder unbinder;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layout = getLayout();
        if (layout <= 0) {
            throw new RuntimeException("layout id not allow 0 or<0");
        } else {
            setContentView(getLayout());
        }

        unbinder = ButterKnife.bind(this);
        presenter = createPresenter();

        if (presenter != null) {
            presenter.attachView(this);
        }
        initView();
        initData();
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract P createPresenter();

    @Override
    public void showLoading(int visible) {

    }

    @Override
    public void showToast(String tips) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (presenter != null) {
            presenter.unAttachView();
        }
    }
}
