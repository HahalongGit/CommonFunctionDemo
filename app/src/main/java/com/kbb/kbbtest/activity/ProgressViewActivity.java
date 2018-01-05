package com.kbb.kbbtest.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.widget.ProgressView;

public class ProgressViewActivity extends AppCompatActivity {

    private ProgressView progressView;

    private Button btnChangeColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_view);
        progressView = (ProgressView) findViewById(R.id.prgressView);
        btnChangeColor = (Button) findViewById(R.id.btn_changeColor);
        btnChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProgressViewActivity.this, "点击改变颜色", Toast.LENGTH_SHORT).show();
                progressView.setProgressColor(Color.parseColor("#447711"));
            }
        });
        //progressView.setProgress(2);
        progressView.setProgressColor(Color.parseColor("#992211"));
    }
}
