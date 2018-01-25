package com.bawei.asus.myokhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
   }
    /**
     * OKHttp的Get网络请求（同步）
     *
     * @param view
     */
    public void doGetRequest_back(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //实例化OkHttpClient
                    OkHttpClient okHttpClient = new OkHttpClient();
                    //通过Request的builder对象设置Get请求的请求地址返回Request对象
                    Request request = new Request.Builder().url("http://apicloud.mob.com/v1/weather/type?key=22ecf6c32440e").build();
                    //通过newCall方法获取到Call对象
                    Call call = okHttpClient.newCall(request);
                    //通过call去进行网络请求并返回响应结果
                    Response response = call.execute();
                    //判断响应是否正确
                    if (response.isSuccessful()) {
                        //获取到响应体
                        ResponseBody responseBody = response.body();
                        //根据相应体获取到相应内容
                        String jsonStr = responseBody.string();
                        Log.i("t", "result=" + jsonStr);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    /**
     * OKHttp的Get网络请求（异步）
     *
     * @param view
     */
    public void doGetRequest(View view){
        //实例化
        OkHttpClient okHttpClient = new OkHttpClient();
        //通过Request的builder对象设置Get请求的请求地址返回Request对象
        //默认是get请求 ，get方法可以省略
        Request request = new Request.Builder().get().url("http://apicloud.mob.com/v1/weather/type?key=22ecf6c32440e").build();

    }
}
