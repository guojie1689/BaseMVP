package com.gj.mvp;

import android.app.Application;
import android.content.Context;

/**
 * @author guojie
 * <p>
 */
public class MvpApplication extends Application {

    private static Context context = null;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        context = this;
    }
}
