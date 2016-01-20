package com.ptteng.pt.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by xnyu on 2016/1/18.
 */
public class HttpUtil2 {


    public interface CallBack {
        void onRequestComplete(String result);
    }

    public static void doPostAsyn(final String url, final String param, final CallBack callBack) throws Exception{
        new Thread(){
            @Override
            public void run() {
                String result = doPost(url,param);
                if (callBack!=null){
                    callBack.onRequestComplete(result);
                }
            }
        }.start();
    }

    public static String doPost(String url, String param) {
        HttpURLConnection urlConnection = null;
        String result = "";
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", ("application/xml; charset=utf-8").replaceAll("\\s", ""));
            urlConnection.setRequestProperty("Content-Length", String.valueOf(param.getBytes().length));
            OutputStream out = urlConnection.getOutputStream();
            out.write(param.getBytes());
            out.close();
            int responseCode = urlConnection.getResponseCode();
            BufferedReader in = null;
            if (responseCode == 200) {
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            } else {
                in = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream()));
            }

            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }

        return result;
    }
}
