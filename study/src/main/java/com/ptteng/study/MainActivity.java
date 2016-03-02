package com.ptteng.study;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
//            String str = (String) msg.obj;
            mTv.setText((CharSequence) msg.obj);
        }
    };
    private TextView mTv;
    private AutoCompleteTextView mActv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mActv = (AutoCompleteTextView) findViewById(R.id.actv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line);

        //        new Thread(){
//            @Override
//            public void run() {
////                http://120.132.71.211/a/user?phone=123456&verify=5555&password=123456
//                try {
//                    String data = "phone=123456&verify=5555&password=123456";
//                    URL url= new URL("http://120.132.71.211/a/user?");
//                    System.out.println(2222);
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("POST");
//                    conn.setConnectTimeout(10000);
////                    conn.setReadTimeout(5000);
//                    conn.setDoOutput(true);
//                    conn.getOutputStream().write(data.getBytes());
//                    conn.connect();
//                    //                    conn.getResponseCode();
//                    System.out.println(conn.getResponseCode());
//                    System.out.println(conn.getResponseMessage());
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    System.out.println(1111);
//                    while ((line=br.readLine())!=null){
//                        response.append(line);
//                    }
//                    System.out.println(response);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();


//        new Thread() {
//            @Override
//            public void run() {
//                try {
//                    URL url = new URL("http://www.baidu.com");
//                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                    conn.setRequestMethod("GET");
//                    conn.setConnectTimeout(5000);
//                    BufferedReader bfr = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    StringBuilder response = new StringBuilder();
//                    String line;
//                    if (conn.getResponseCode()==200){
//                        while ((line=bfr.readLine())!=null){
//                            response.append(line);
//                        }
//
//                        Message msg = Message.obtain();
//                        System.out.println(response);
//                        msg.obj = response;
//                        mHandler.sendMessage(msg);
//                    }
//
//                    bfr.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }


}
