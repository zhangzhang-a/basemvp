package com.example.basemvp.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.basemvp.Ibase.IBasePresenter;
import com.example.basemvp.Ibase.IBaseView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {
    protected P presenter;
    Unbinder unbinder;
    protected Context context;
    protected Activity activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = getContext();
        activity = getActivity();
        int layout = getLayout();
        View view = null;
        if (layout <= 0) {
            throw new RuntimeException("布局非法");
        } else {
            view = inflater.inflate(layout, container, false);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = createPresenter();

        unbinder = ButterKnife.bind(this, view);
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
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.unAttachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
        context = null;
        activity = null;

    }
}
