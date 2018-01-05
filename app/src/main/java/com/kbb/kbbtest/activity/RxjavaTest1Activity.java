package com.kbb.kbbtest.activity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.bean.TestEntity;
import com.kbb.kbbtest.utils.HttpMethods;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RxjavaTest1Activity extends AppCompatActivity {

    private static final String TAG = "RxjavaTest1Activity";

    private Subscriber subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava_test1);
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }).dispose();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        retrofit.create(LoginService.class);
    }

    public interface LoginService{
        @GET("top250")
        Observable<String> getJsonString(@Query("start") int start,@Query("end") int end);
    }

    @Override
    protected void onResume() {
        super.onResume();
        HttpMethods httpMethods = HttpMethods.getInstance();
        Observer<TestEntity> observer = new Observer<TestEntity>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                d.dispose();
            }

            @Override
            public void onNext(@NonNull TestEntity testEntity) {

            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        httpMethods.getTestEntity(observer,1,2);
    }
}
