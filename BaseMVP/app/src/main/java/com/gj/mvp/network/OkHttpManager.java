package com.gj.mvp.network;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author guojie
 * <p>
 * OkhttpManager
 */
public class OkHttpManager {

    private OkHttpClient client = null;

    private OkHttpManager() {
        initHttpClient();
    }

    private void initHttpClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(NetworkConfig.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.sslSocketFactory(createSSLSocketFactory());
        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        });

        client = builder.build();
    }

    public static class HOLDER {
        public static OkHttpManager instance = new OkHttpManager();
    }

    public static OkHttpManager getInstance() {
        return OkHttpManager.HOLDER.instance;
    }

    public <T> void request(String url, final IJsonResponseCallback<T> callback, final Class<T> classOfT) {

        Request request = new Request.Builder().get().url(url).build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    callback.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callback == null) {
                    return;
                }

                if (!response.isSuccessful()) {
                    callback.onFailure("服务器访问失败");
                    return;
                }

                if (response.body() != null) {
                    callback.onResponse(new Gson().fromJson(response.body().string(), classOfT));
                } else {
                    callback.onResponse(null);
                }
            }
        });
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }

}
