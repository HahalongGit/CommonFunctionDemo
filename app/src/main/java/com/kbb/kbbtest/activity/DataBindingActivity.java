package com.kbb.kbbtest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.bean.User;

public class DataBindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);
        //DataBindingActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        //DataBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
       // User user = new User();


    }
}
