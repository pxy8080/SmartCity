package com.example.smartcity1222;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtil {
    public static String host = "http://" + MyApplication.getSharedPreferences().getString("ip", "124.93.196.45") + ":" + MyApplication.getSharedPreferences().getString("port", "10001");

    public static void onHttp(Request request, HttpBack httpBack) {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    httpBack.onSuccess(null, "网络异常!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    String json = response.body().string();
                    JSONObject jsonObject = new JSONObject(json);
                    if (jsonObject.getInt("code")==200){
                        httpBack.onSuccess(jsonObject, json);
                    }else {
                        httpBack.onFail(jsonObject,json);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void doTest(String url,HttpBack httpBack){
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        onHttp(request,httpBack);
    }

    public static void doGet(String url,HttpBack httpBack){
        Request request = new Request.Builder()
                .url(HttpUtil.host+url)
                .addHeader("Authorization",MyApplication.getSharedPreferences().getString("token",""))
                .get()
                .build();
        onHttp(request,httpBack);
    }

    public static void doPost(String url, Map<String,Object> map,HttpBack httpBack){
        String json = MyApplication.getGson().toJson(map);
        Request request = new Request.Builder()
                .url(HttpUtil.host+url)
                .addHeader("Authorization",MyApplication.getSharedPreferences().getString("token",""))
                .post(RequestBody.create(MediaType.parse("application/json"),json))
                .build();
        onHttp(request,httpBack);
    }

    public static void doPut(String url, Map<String,Object> map,HttpBack httpBack){
        String json = MyApplication.getGson().toJson(map);
        Request request = new Request.Builder()
                .url(HttpUtil.host+url)
                .addHeader("Authorization",MyApplication.getSharedPreferences().getString("token",""))
                .put(RequestBody.create(MediaType.parse("application/json"),json))
                .build();
        onHttp(request,httpBack);
    }

    public static void uploadFile(String url, File file,HttpBack httpBack){
        RequestBody requestBody;
        MultipartBody.Builder builder = new MultipartBody.Builder();
        if (file ==null){
            requestBody= builder.build();
        }else {
            requestBody = builder.addFormDataPart("file",file.getAbsolutePath(),RequestBody.create(MediaType.parse("application/octet-stream"),file)).build();
        }
        Request request = new Request.Builder()
                .url(HttpUtil.host+url)
                .addHeader("Authorization",MyApplication.getSharedPreferences().getString("token",""))
                .post(requestBody)
                .build();
        onHttp(request,httpBack);
    }
}
