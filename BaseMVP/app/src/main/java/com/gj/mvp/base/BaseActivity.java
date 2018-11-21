package com.gj.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @param <V>
 * @param <P>
 * @author guojie
 */
public abstract class BaseActivity<V extends IBaseView, P extends BasePresenter<V>> extends FragmentActivity
        implements IBaseView {

    protected P mPresenter = null;
    private Unbinder unbinder = null;

    /**
     * 创建业务类
     *
     * @return
     */
    protected abstract P createPresenter();

    /**
     * 提供部署文件ID
     *
     * @return
     */
    protected abstract int providerContentViewId();

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int layoutId = providerContentViewId();
        if (layoutId > 0) {
            setContentView(layoutId);
        }

        unbinder = ButterKnife.bind(this);

        mPresenter = createPresenter();
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }

        initView();
        initData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(unbinder!=null){
            unbinder.unbind();
        }

        if (mPresenter != null) {
            getLifecycle().removeObserver(mPresenter);

            mPresenter.deatchView();
        }
    }

}
