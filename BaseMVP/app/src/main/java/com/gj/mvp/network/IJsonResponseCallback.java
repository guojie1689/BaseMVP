package com.gj.mvp.network;

/**
 * @author guojie
 * <p>
 */
public interface IJsonResponseCallback<T> {

    void onFailure(String errorMsg);

    void onResponse(T body);

}
