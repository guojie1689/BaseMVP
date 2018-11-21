package com.gj.mvp.base;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.Nullable;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author guojie
 */
public class BasePresenter<V extends IBaseView> implements IViewLifeCycle {

    protected Reference<V> mViewRef;

    public BasePresenter(V view) {
        this.attachView(view);
    }

    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    public void deatchView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected V getView() {
        return mViewRef != null ? mViewRef.get() : null;
    }

    @Override
    public void onViewCreate(@Nullable LifecycleOwner owner) {

    }

    @Override
    public void onViewStart(@Nullable LifecycleOwner owner) {

    }

    @Override
    public void onViewResume(@Nullable LifecycleOwner owner) {

    }

    @Override
    public void onViewPause(@Nullable LifecycleOwner owner) {

    }

    @Override
    public void onViewStop(@Nullable LifecycleOwner owner) {

    }

    @Override
    public void onViewDestory(@Nullable LifecycleOwner owner) {

    }
}
