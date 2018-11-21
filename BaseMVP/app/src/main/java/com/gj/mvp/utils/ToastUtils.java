package com.gj.mvp.utils;

import android.widget.Toast;

import com.gj.mvp.MvpApplication;

/**
 * @author guojie
 * <p>
 * Toast 弹出框工具类
 */
public class ToastUtils {

    public static void showToastShort(String str) {
        Toast toast = Toast.makeText(MvpApplication.getContext(), str, Toast.LENGTH_SHORT);

        toast.show();
    }

    public static void showToastLong(String str) {
        Toast toast = Toast.makeText(MvpApplication.getContext(), str, Toast.LENGTH_LONG);

        toast.show();
    }

    public static void showToastShort(int stringResId) {
        showToastShort(MvpApplication.getContext().getString(stringResId));
    }

    public static void showToastLong(int stringResId) {
        showToastLong(MvpApplication.getContext().getString(stringResId));
    }
}

