package com.ptteng.pt.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ptteng.pt.R;
import com.ptteng.pt.Util.HttpUtils;
import com.ptteng.pt.Util.T;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by xnyu on 2016/1/18.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mPhoneEt;
    private EditText mPasswordEt;
    private Button mLoginBtn;
    private Button mCloseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPhoneEt = (EditText) findViewById(R.id.login_phone_et);
        mPasswordEt = (EditText) findViewById(R.id.login_password_et);
        mLoginBtn = (Button) findViewById(R.id.login_login_btn);
        mCloseBtn = (Button) findViewById(R.id.login_close_btn);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.finish();
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mPasswordEt.getText()) || TextUtils.isEmpty(mPhoneEt.getText())) {
                    T.showShort(LoginActivity.this,"手机号或密码不能为空");
                    return;
                }
                OkHttpUtils.post().url(HttpUtils.URL+"/a/login").addParams("phone",mPhoneEt.getText().toString())
                        .addParams("password",mPasswordEt.getText().toString()).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                });
            }
        });
    }
}
