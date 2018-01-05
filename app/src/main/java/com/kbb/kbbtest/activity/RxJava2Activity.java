package com.kbb.kbbtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.kbb.kbbtest.R;
import com.kbb.kbbtest.bean.MobileAddress;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RxJava2Activity extends AppCompatActivity {

    private static final String TAG = "RxJava2Activity";

    /**
     * 现实请求网络的结果
     */
    @BindView(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java2);
        ButterKnife.bind(this);
        tvShow.setText("修改一个");

        getSomeData();

    }

    private void getSomeData() {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Response> e) throws Exception {
                Log.e(TAG, "当前的线程subscribe：" + Thread.currentThread().getName());
                Request.Builder builder = new Request.Builder()
                        .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                        .get();
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                e.onNext(response);
            }
        }).map(new Function<Response, MobileAddress>() {
            @Override
            public MobileAddress apply(@NonNull Response response) throws Exception {
                ResponseBody body = response.body();
                Log.e(TAG, "当前的线程Function：" + Thread.currentThread().getName());
                if (body != null) {
                    MobileAddress mobileAddress = new Gson().fromJson(body.string(), MobileAddress.class);
                    Log.e(TAG, "Function-body != null--" + mobileAddress.getResult().getMobilearea());
                    return mobileAddress;
                }
                return null;
            }
        }).doOnNext(new Consumer<MobileAddress>() {
            @Override
            public void accept(MobileAddress mobileAddress) throws Exception {
                Log.e(TAG, "doNext 操作：");
                Log.e(TAG, "当前的线程doOnNext：" + Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(MobileAddress mobileAddress) throws Exception {
                        Log.e(TAG, "保存成功！" + Thread.currentThread().getName());
                        tvShow.setText(mobileAddress.getResult().getMobilearea() + "," + mobileAddress.getResult().getMobilenumber());

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "请求失败-accept！" + Thread.currentThread().getName());
                        Log.e(TAG, "请求失败-accept！" + throwable.toString() + "," + throwable.getCause());
                    }
                });
    }
}
