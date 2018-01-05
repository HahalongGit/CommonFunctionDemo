package com.kbb.kbbtest.activity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.utils.MediaManager;
import com.kbb.kbbtest.utils.PermissionHelper;
import com.kbb.kbbtest.widget.AudioManager;
import com.kbb.kbbtest.widget.AudioRecordButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordTestActivity extends AppCompatActivity {

    private static final String TAG = "RecordTestActivity";

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.btn_record)
    AudioRecordButton btnRecord;
    @BindView(R.id.btn_play)
    Button btnPlay;
    SharedPreferences preferences;

    private PermissionHelper helper;

    private List<String> permissionList = new ArrayList<>();

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_test);
        ButterKnife.bind(this);
        preferences = getSharedPreferences("filePath",MODE_PRIVATE);
        editor = preferences.edit();

        btnRecord.setHasRecordPromission(false);
        //permissionHelper();
        btnRecord.setAudioFinishRecorderListener(new AudioRecordButton.AudioFinishRecorderListener() {
            @Override
            public void onFinished(float seconds, String filePath) {
                Toast.makeText(RecordTestActivity.this, "录音结束", Toast.LENGTH_SHORT).show();
                Log.e("TAG","filePath:"+filePath);
                editor.putString("filePath",filePath);
                editor.commit();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //helper.handleRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        Log.e(TAG, "result:" + result);
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能录音", Toast.LENGTH_SHORT).show();
                            //return;
                        }
                    }
                    btnRecord.setHasRecordPromission(true);
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onPause() {
        MediaManager.release();
        super.onPause();
    }

    private void permissionRequest() {
        Log.e("TAG", "permissionRequest");
        if (ActivityCompat.checkSelfPermission(RecordTestActivity.this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.RECORD_AUDIO);
        }
        if (ActivityCompat.checkSelfPermission(RecordTestActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            Log.e("TAG", "permissionSize:" + permissionList.size());
            String[] permisson = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(RecordTestActivity.this, permisson, 1);
        } else {
            btnRecord.setHasRecordPromission(true);
        }
    }

    @OnClick({R.id.btn_record,R.id.btn_play})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_record:{
                Log.e("TAG", "点击授权");
                permissionRequest();
                break;
            }
            case R.id.btn_play:{
                MediaManager.release();
                Log.e("TAG","获取文件路径："+preferences.getString("filePath",null));
                MediaManager.playSound(preferences.getString("filePath",null),
                        new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                Log.e("TAG","播放完成");
                                Toast.makeText(RecordTestActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
            }
        }

    }

}
