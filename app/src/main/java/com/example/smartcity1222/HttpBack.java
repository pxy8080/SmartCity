package com.example.smartcity1222;

import org.json.JSONObject;

public interface HttpBack {
    void onSuccess(JSONObject jsonObject,String response)throws Exception;
    void onFail(JSONObject jsonObject,String response)throws Exception;
}
