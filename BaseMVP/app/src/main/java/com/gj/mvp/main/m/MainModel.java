package com.gj.mvp.main.m;

import com.gj.mvp.network.IJsonResponseCallback;
import com.gj.mvp.network.OkHttpManager;

/**
 * @author guojie
 * <p>
 */
public class MainModel {

    private final String URL = "http://open.iciba.com/dsapi/";

    public void requestSentenceInfo(IJsonResponseCallback<SentenceInfo> responseCallback) {

        OkHttpManager.getInstance().request(URL, responseCallback, SentenceInfo.class);

    }

}
