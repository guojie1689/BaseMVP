package com.gj.mvp.main.p;

import android.arch.lifecycle.LifecycleOwner;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.gj.mvp.base.BasePresenter;
import com.gj.mvp.main.m.MainModel;
import com.gj.mvp.main.m.SentenceInfo;
import com.gj.mvp.main.v.IMainView;
import com.gj.mvp.network.IJsonResponseCallback;
import com.gj.mvp.utils.ToastUtils;

/**
 * @author guojie
 * <p>
 */
public class MainPresenter extends BasePresenter<IMainView> {

    private MainModel mainModel = null;
    private Handler handler = null;

    public MainPresenter(IMainView view) {
        super(view);

        mainModel = new MainModel();
        handler = new Handler();
    }

    @Override
    public void onViewResume(@Nullable LifecycleOwner owner) {
        super.onViewResume(owner);

        requestData();
    }

    public void requestData() {

        mainModel.requestSentenceInfo(new IJsonResponseCallback<SentenceInfo>() {
            @Override
            public void onFailure(String errorMsg) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.showToastShort(errorMsg);
                    }
                });
            }

            @Override
            public void onResponse(SentenceInfo body) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getView().showSentenceInfoInfo(body);
                    }
                });
            }
        });
    }
}
