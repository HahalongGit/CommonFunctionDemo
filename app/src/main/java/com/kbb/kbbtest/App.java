package com.kbb.kbbtest;

import android.app.Application;

import org.litepal.LitePal;

import butterknife.ButterKnife;

/**
 * Created by 龙龙同学 on 2017/7/28.
 *
 * @ClassName: App
 * @Description:
 * @Date 2017/7/28
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
