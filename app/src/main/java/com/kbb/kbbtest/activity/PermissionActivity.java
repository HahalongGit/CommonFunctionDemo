package com.kbb.kbbtest.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.BoolRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kbb.kbbtest.R;
import com.kbb.kbbtest.utils.DialogHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

public class PermissionActivity extends AppCompatActivity {

    private static final String TAG = "PermissionActivity";
    @BindView(R.id.btn_call)
    Button btnCall;
    @BindView(R.id.button2)
    Button button2;

    private final int REQUEST_CODE = 1001;

    private static final int PERMISSION_ID = 0x0001;

    private List<String> permissionList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_call, R.id.button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_call:
                callPhone();
                break;
            case R.id.button2:
                Toast.makeText(this, "按钮点击", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void callPhone() {
        Log.e(TAG, "点击打电话");
        try {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.CAMERA);
            }
            if (ContextCompat.checkSelfPermission(PermissionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(Manifest.permission.CALL_PHONE);
            }
            if (!permissionList.isEmpty()) {
                String[] permissions = permissionList.toArray(new String[permissionList.size()]);
                ActivityCompat.requestPermissions(PermissionActivity.this, permissions, 1);
            } else {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:10000"));
                startActivity(intent);
            }
        } catch (Exception e) {
            Log.e(TAG, "callPhone：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "需要所有的权限", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:10000"));
                    startActivity(intent);
                }
                break;
            }
        }
    }

}
