package com.gj.mvp.main.v;

import android.widget.TextView;

import com.gj.mvp.R;
import com.gj.mvp.base.BaseActivity;
import com.gj.mvp.main.m.SentenceInfo;
import com.gj.mvp.main.p.MainPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<IMainView, MainPresenter> implements IMainView {

    @BindView(R.id.txt_sid)
    protected TextView txt_sid;

    @BindView(R.id.txt_content)
    protected TextView txt_content;

    @BindView(R.id.txt_note)
    protected TextView txt_note;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int providerContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.button_get_data)
    protected void getData() {
        mPresenter.requestData();
    }

    @OnClick(R.id.button_jump_to_detail)
    protected void jumpToDetail() {
    }

    @Override
    public void showSentenceInfoInfo(SentenceInfo sentenceInfo) {
        txt_sid.setText(sentenceInfo.getSid());
        txt_content.setText(sentenceInfo.getContent());
        txt_note.setText(sentenceInfo.getNote());
    }
}
