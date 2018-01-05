package com.kbb.kbbtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kbb.kbbtest.activity.BannerTestActivity;
import com.kbb.kbbtest.activity.BottomBarTestActivity;
import com.kbb.kbbtest.activity.DataBindingActivity;
import com.kbb.kbbtest.activity.MediaRecordActivity;
import com.kbb.kbbtest.activity.PermissionActivity;
import com.kbb.kbbtest.activity.ProgressViewActivity;
import com.kbb.kbbtest.activity.RecordTestActivity;
import com.kbb.kbbtest.activity.RecyclerViewActivity;
import com.kbb.kbbtest.activity.RxJava2Activity;
import com.kbb.kbbtest.activity.RxJavaRetrofitActivity;
import com.kbb.kbbtest.activity.ShadowActivity;
import com.kbb.kbbtest.bean.UserInfo;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cookie;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.btn_submit)
    Button btnSubmit;

    @BindView(R.id.btn_retrofit)
    Button btnRetrofit;
    @BindView(R.id.btn_bottomBar)
    Button btnBottomBar;
    @BindView(R.id.btn_recyclerView)
    Button btnRecyclerView;
    @BindView(R.id.btn_permission)
    Button btnPermission;
    @BindView(R.id.btn_recordtest)
    Button btnRecordtest;
    @BindView(R.id.btn_audio)
    Button btnAudio;
    @BindView(R.id.btn_banner)
    Button btnBanner;
    @BindView(R.id.btn_progressView)
    Button btnProgressView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Connector.getDatabase();

        try {
            UserInfo userInfo = new UserInfo();
            userInfo.setPassword("123456aa");
            userInfo.setUsername("lllAA龙龙");
            userInfo.setType("type11");
            userInfo.saveOrUpdate();
            Log.e("TAG","userInfo.saveOrUpdate()");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @OnClick({R.id.tv_text, R.id.btn_submit, R.id.btn_retrofit, R.id.btn_bottomBar,
            R.id.btn_recyclerView, R.id.btn_permission, R.id.btn_recordtest,
            R.id.btn_audio, R.id.btn_banner,R.id.btn_progressView,R.id.btn_shadow,R.id.btn_dataBinding})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_text:
                Toast.makeText(this, "点击文字", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_submit:
                Intent intent = new Intent();
                intent.setClass(this, RxJava2Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_retrofit: {
                RxJavaRetrofitActivity.goNextActivity(this);
                break;
            }
            case R.id.btn_bottomBar: {
                Intent intentBottomBar = new Intent(MainActivity.this, BottomBarTestActivity.class);
                startActivity(intentBottomBar);
                break;
            }
            case R.id.btn_recyclerView: {
                Intent intent2 = new Intent(MainActivity.this, RecyclerViewActivity.class);
                startActivity(intent2);
                break;
            }
            case R.id.btn_permission: {
                Intent intent3 = new Intent(MainActivity.this, PermissionActivity.class);
                startActivity(intent3);
                break;
            }
            case R.id.btn_recordtest: {
                Intent intent3 = new Intent(MainActivity.this, RecordTestActivity.class);
                startActivity(intent3);
                break;
            }
            case R.id.btn_audio: {
                Intent intentAudio = new Intent(MainActivity.this, MediaRecordActivity.class);
                startActivity(intentAudio);
                break;
            }
            case R.id.btn_banner: {
                Intent bannerIntent = new Intent(MainActivity.this, BannerTestActivity.class);
                startActivity(bannerIntent);
                break;
            }
            case R.id.btn_progressView:{
                Intent bannerIntent = new Intent(MainActivity.this, ProgressViewActivity.class);
                startActivity(bannerIntent);
                break;
            }
            case R.id.btn_shadow:{
                Intent bannerIntent = new Intent(MainActivity.this, ShadowActivity.class);
                startActivity(bannerIntent);
                break;
            }
            case R.id.btn_dataBinding:{
                Intent bannerIntent = new Intent(MainActivity.this, DataBindingActivity.class);
                startActivity(bannerIntent);
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Log.e("TAG","获取的UserInfo："+DataSupport.findFirst(UserInfo.class).getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_banner)
    public void onViewClicked() {
    }
}
