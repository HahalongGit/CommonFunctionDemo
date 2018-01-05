package com.kbb.kbbtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kbb.kbbtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

public class BannerTestActivity extends AppCompatActivity {

    @BindView(R.id.bannerPage)
    BGABanner bannerPage;
    @BindView(R.id.tv_otherText)
    TextView tvOtherText;

    private List<String> imageList = new ArrayList<>();
    private List<String> tipsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_test);
        ButterKnife.bind(this);
        imageList.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1403/01/c0/31671078_1393641119421.jpg");
        imageList.add("http://p5.so.qhimgs1.com/bdr/326__/t017c03abd1aaf7dbeb.jpg");
        imageList.add("http://p4.so.qhmsg.com/t01185f568f2f064f39.jpg");
        tipsList.add("图片1");
        tipsList.add("图片2");
        tipsList.add("图片3");
        bannerPage.setData(imageList,null);
        bannerPage.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, Object model, int position) {
                Log.e("TAG","fillBannerItem:"+model);
                Glide.with(banner.getContext())
                        .load(model)
                       /// .thumbnail(0.1f)
                        .into((ImageView) itemView);
            }
        });
    }
}
