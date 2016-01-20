package com.ptteng.pt.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;

/**
 * Created by xnyu on 2016/1/17.
 */
public class RegisterActivity extends AppCompatActivity {

    private Button mGetAuth;
    private TimeCount mTimeCount;
    private EditText mPhoneEt;
    private EditText mPasswordEt;
    private Button mCloseBtn;
    private Button mLoginBtn;
    private Button mRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mGetAuth = (Button) findViewById(R.id.register_get_auth_btn);
        mPhoneEt = (EditText) findViewById(R.id.register_phone_et);
        mPasswordEt = (EditText) findViewById(R.id.register_password_et);
        mCloseBtn = (Button) findViewById(R.id.register_close_btn);
        mLoginBtn = (Button) findViewById(R.id.register_login_btn);
        mRegisterBtn = (Button) findViewById(R.id.register_register);
        mCloseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        mTimeCount = new TimeCount(60000, 1000);
        mGetAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTimeCount.start();
                OkHttpUtils.post().url(HttpUtils.URL + "/a/code/send").addParams("phone", mPhoneEt.getText().toString())
                        .addParams("type", "register").build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        int code;
                        String msg;
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            code = jsonObject.getInt("code");
                            msg = jsonObject.getString("message");
                            if (code != 200) {
                                T.showShort(RegisterActivity.this, msg);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });

            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mPasswordEt.getText()) || TextUtils.isEmpty(mPhoneEt.getText())) {
                    T.showShort(RegisterActivity.this,"手机号或密码不能为空");
                    return;
                }
                OkHttpUtils.post().url(HttpUtils.URL+"/a/user").addParams("phone",mPhoneEt.getText().toString())
                        .addParams("verify",mGetAuth.getText().toString())
                        .addParams("password",mPasswordEt.getText().toString()).build().execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        System.out.println(e);
                    }

                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                    }
                });
            }
        });
    }

    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {// 计时完毕
            mGetAuth.setClickable(true);
            mGetAuth.setText("获取验证码");
        }

        @Override
        public void onTick(long millisUntilFinished) {// 计时过程
            mGetAuth.setClickable(false);//防止重复点击
            mGetAuth.setText(millisUntilFinished / 1000 + "秒后可重发");
        }
    }
}
