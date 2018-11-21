package com.gj.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends IBaseView, P extends BasePresenter<V>> extends Fragment {


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
     * 初始化View
     *
     * @param view
     * @param savedInstanceState
     */
    protected abstract void initView(View view, Bundle savedInstanceState);

    protected P mPresenter = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(providerContentViewId(), container, false);

        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = createPresenter();

        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(unbinder!=null){
            unbinder.unbind();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {

            getLifecycle().removeObserver(mPresenter);

            mPresenter.deatchView();
        }
    }
}
