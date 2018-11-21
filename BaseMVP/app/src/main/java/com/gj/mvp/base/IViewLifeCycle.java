package com.gj.mvp.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.annotation.Nullable;

/**
 * @author guojie
 * <p>
 */
public interface IViewLifeCycle extends LifecycleObserver {

    /**
     * Activity/Fragment onCreate
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onViewCreate(@Nullable LifecycleOwner owner);

    /**
     * Activity/Fragment onStart
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onViewStart(@Nullable LifecycleOwner owner);

    /**
     * Activity/Fragment onResume
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onViewResume(@Nullable LifecycleOwner owner);

    /**
     * Activity/Fragment onPause
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onViewPause(@Nullable LifecycleOwner owner);

    /**
     * Activity/Fragment onStop
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onViewStop(@Nullable LifecycleOwner owner);

    /**
     * Activity/Fragment onDestory
     *
     * @param owner
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onViewDestory(@Nullable LifecycleOwner owner);

}
