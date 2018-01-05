package com.kbb.kbbtest.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.bean.ProductInfo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MediaRecordActivity extends AppCompatActivity {

    @BindView(R.id.btn_audio)
    Button btnAudio;
    @BindView(R.id.btn_playAudio)
    Button btnPlayAudio;

    private MediaRecorder mediaRecorder;

    private MediaPlayer mediaPlayer;

    /**
     * 是否开始录音
     */
    private boolean isStartRecord;

    private File audioPath = Environment.getExternalStoragePublicDirectory("longlong_audio");

    private String filePath;

    private boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_record);
        ButterKnife.bind(this);
        if(!audioPath.exists()){
            audioPath.mkdirs();
        }

    }

    private void initRecord() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        File file = new File(audioPath, UUID.randomUUID()+".arm");
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        filePath = file.getAbsolutePath();
        Log.e("TAG","文件的路径是："+filePath);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("TAG","media prepare fial");
        }
        mediaRecorder.start();
    }

    /**
     * 停止录音
     */
    private void stopRecord() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }


    @OnClick({R.id.btn_audio, R.id.btn_playAudio})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_audio:
                if(!isStartRecord){
                    isStartRecord = true;
                    btnAudio.setText("停止");
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO},1);
                    }else {
                        Toast.makeText(this, "开始录音", Toast.LENGTH_SHORT).show();
                        initRecord();
                    }
                }else{
                    Toast.makeText(this, "停止录音", Toast.LENGTH_SHORT).show();
                    isStartRecord = false;
                    btnAudio.setText("重新开始");
                    stopRecord();
                }
                break;
            case R.id.btn_playAudio:
                if(!isPause){
                    play();
                }else if(mediaPlayer.isPlaying()){
                    isPause = false;
                    mediaPlayer.pause();
                    btnPlayAudio.setText("播放");
                }
                break;
        }
    }

    private void play() {
        if(mediaPlayer==null){
            mediaPlayer = new MediaPlayer();
        }
        if(!TextUtils.isEmpty(filePath)){
            try {
                mediaPlayer.setDataSource(filePath);
                mediaPlayer.prepare();
                mediaPlayer.start();
                btnPlayAudio.setText("停止");
                isPause = true;
                Log.e("TAG","mediaPlayer time second:"+mediaPlayer.getDuration()/1000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Toast.makeText(this, "没有找到音乐源", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:{
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "授权后开始录音", Toast.LENGTH_SHORT).show();
                    initRecord();
                }else{
                    Toast.makeText(this, "需要授权才能使用录音", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
