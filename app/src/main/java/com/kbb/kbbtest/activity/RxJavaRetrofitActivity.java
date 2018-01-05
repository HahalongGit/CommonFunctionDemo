package com.kbb.kbbtest.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.bean.MobileAddress;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RxJavaRetrofitActivity extends BaseActivity {

    private static final String TAG = "RxJavaRetrofitActivity";
    @BindView(R.id.tv_retrofit)
    TextView tvRetrofit;

    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_retrofit);
        ButterKnife.bind(this);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.avatardata.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        MobileAddressService mobileAddressService = retrofit.create(MobileAddressService.class);
       // rxJavaRetrofit2Call(mobileAddressService);
        //generalRetrofitCall(mobileAddressService);
        pathMethodRetrofitCall(mobileAddressService);
    }

    /**
     * path 注释方式请求。
     * @param mobileAddressService
     */
    private void pathMethodRetrofitCall(MobileAddressService mobileAddressService) {
        mobileAddressService.getMobileAddress3("ec47b85086be4dc8b5d941f5abd37a4e","13021671512")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(MobileAddress mobileAddress) throws Exception {
                        if(mobileAddress!=null){
                            tvRetrofit.setText("@path 方式结果是："+mobileAddress.getResult().getMobilearea()+"-"+mobileAddress.getResult().getAreacode());
                        }
                        Log.e("TAG", "getMobileAddress3：" + mobileAddress.getError_code());
                        Log.e("TAG", "getMobileAddress3：" + mobileAddress.getResult().getMobilearea());
                    }
                });
    }

    private void generalRetrofitCall(MobileAddressService mobileAddressService) {
        Call<MobileAddress> call = mobileAddressService.getMobileAddress4("ec47b85086be4dc8b5d941f5abd37a4e", "13021671512");
        call.enqueue(new Callback<MobileAddress>() {
            @Override
            public void onResponse(Call<MobileAddress> call, Response<MobileAddress> response) {
                MobileAddress body = response.body();
                if(body!=null){
                    tvRetrofit.setText("enqueue方式："+body.getResult().getMobilearea()+"-"+body.getResult().getMobilenumber());
                }
            }

            @Override
            public void onFailure(Call<MobileAddress> call, Throwable t) {
                Log.e(TAG,"onFailure:"+t.getMessage());
            }
        });
    }

    private void rxJavaRetrofit2Call(MobileAddressService mobileAddressService) {
        mobileAddressService.getMobileAddress2("ec47b85086be4dc8b5d941f5abd37a4e", "13021671512")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(MobileAddress mobileAddress) throws Exception {
                        if(mobileAddress!=null){
                            tvRetrofit.setText("rxJava方式结果是："+mobileAddress.getResult().getMobilearea()+"-"+mobileAddress.getResult().getAreacode());
                        }

                        Log.e("TAG", "accept1：" + mobileAddress.getError_code());
                        Log.e("TAG", "accept2：" + mobileAddress.getResult().getMobilearea());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("TAG", "throwable：" + throwable.getMessage());
                    }
                });
    }


    public static void goNextActivity(Context context) {
        Intent intent = new Intent(context, RxJavaRetrofitActivity.class);
        context.startActivity(intent);
    }

    interface MobileAddressService {
        @GET("/mobileAddress")
        Observable<MobileAddress> getMobileAddress1();

        @GET("MobilePlace/LookUp")
        Observable<MobileAddress> getMobileAddress2(@Query("key") String key, @Query("mobileNumber") String mobileNumber);

        @GET("MobilePlace/LookUp")
        Call<MobileAddress> getMobileAddress4(@Query("key") String key, @Query("mobileNumber") String mobileNumber);

        @HTTP(method = "GET",
                path = "MobilePlace/LookUp",
                hasBody = false)
        Observable<MobileAddress> getMobileAddress3(@Query("key") String key, @Query("mobileNumber")String mobileNumber);
    }

}
