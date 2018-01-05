package com.kbb.kbbtest.utils;

import com.kbb.kbbtest.bean.TestEntity;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 龙龙同学 on 2017/7/27.
 *
 * @ClassName: HttpMethods
 * @Description:
 * @Date 2017/7/27
 */

public class HttpMethods {
    private static final String TAG = "HttpMethods";
    public static final String BASE_URL = "";
    private Retrofit retrofit;
    private TestEntity testEntity;
    private TestEntityService testEntityService;

    private HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(500, TimeUnit.SECONDS);
        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        testEntityService = retrofit.create(TestEntityService.class);
    }

    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取数据项
     * @param subscriber
     * @param start
     * @param end
     */
    public void getTestEntity(Observer<TestEntity> subscriber, int start , int end){
        testEntityService.getTestEntity(start,end)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public interface TestEntityService{
        @GET("top50")
        Observable<TestEntity> getTestEntity(@Query("start")int start,@Query("end")int end);
    }

}
