package com.example.smartcity1222.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.smartcity1222.HttpBack;
import com.example.smartcity1222.HttpUtil;
import com.example.smartcity1222.MyApplication;
import com.example.smartcity1222.MyHelper;
import com.example.smartcity1222.R;
import com.example.smartcity1222.bean.BackData;
import com.example.smartcity1222.ui.BaseActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class YicheDuCheActivity extends BaseActivity {
    private TextInputEditText carId;
    private TextInputEditText name;
    private TextInputEditText phone;
    private TextInputEditText idCard;
    private TextView select;
    private TextInputEditText address;
    private ImageView paizhao;
    private MaterialButton submit;
    private TextView car_Num;
    private MaterialButton _phone;
    private ImageView history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar("堵路移车",true);
        Map<String, Object> map = new HashMap<>();
        carId = (TextInputEditText) findViewById(R.id.carId);
        history = (ImageView) findViewById(R.id.history);
        name = (TextInputEditText) findViewById(R.id.name);
        phone = (TextInputEditText) findViewById(R.id.phone);
        idCard = (TextInputEditText) findViewById(R.id.idCard);
        select = (TextView) findViewById(R.id.select);
        address = (TextInputEditText) findViewById(R.id.address);
        paizhao = (ImageView) findViewById(R.id.paizhao);
        submit = (MaterialButton) findViewById(R.id.submit);
        history.setOnClickListener(v -> {
            startActivity(new Intent(YicheDuCheActivity.this,HistoryActivity.class));
        });
        paizhao.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA);
        });
        select.setOnClickListener(v -> {
            CityPickerView cityPickerView = new CityPickerView();
            cityPickerView.init(YicheDuCheActivity.this);
            CityConfig.Builder cityConfig = new CityConfig.Builder();
            cityPickerView.setConfig(new CityConfig(cityConfig));
            cityPickerView.showCityPicker();
            cityPickerView.setOnCityItemClickListener(new OnCityItemClickListener() {
                @Override
                public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                    select.setText(province+"-"+city+"-"+district);
                    map.put("province", province.toString());
                    map.put("area", district.toString());
                    map.put("city", city.toString());
                }
            });
        });
        submit.setOnClickListener(v -> {
            map.put("address", address.getText().toString());
            map.put("idCard", idCard.getText().toString());
            map.put("plateNo", carId.getText().toString());
            map.put("tel", phone.getText().toString());
            HttpUtil.doPost("/prod-api/api/park/car/move", map, new HttpBack() {
                @Override
                public void onSuccess(JSONObject jsonObject, String response) throws Exception {
                    address.post(() -> {
                        BackData backData = MyApplication.getGson().fromJson(response, BackData.class);
                        AlertDialog.Builder builder = new AlertDialog.Builder(YicheDuCheActivity.this);
                        View view1 = View.inflate(YicheDuCheActivity.this, R.layout.dialog, null);
                        car_Num = view1.findViewById(R.id.carNum);
                        _phone = view1.findViewById(R.id.phone);
                        builder.setView(view1);
                        builder.show();
                        car_Num.setText(backData.getData().getPlateNo());
                        _phone.setText(backData.getData().getTel());
                        _phone.setOnClickListener(v1 -> {
                            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+_phone.getText().toString()));
                            startActivity(intent);
                        });
                        MyHelper myHelper = new MyHelper(YicheDuCheActivity.this);
                        SQLiteDatabase database = myHelper.getWritableDatabase();
                        database.execSQL("insert into history(carNum ,carMaster ,address)values(?,?,?)"
                                ,new Object[]{backData.getData().getPlateNo(),
                                        backData.getData().getTel(),
                                        select.getText().toString()+address.getText().toString()});
                        database.close();
                    });
                }

                @Override
                public void onFail(JSONObject jsonObject, String response) throws Exception {
                    address.post(() -> {
                        try {
                            MyApplication.showToast(jsonObject.getString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    });
                }
            });
        });
    }

    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA && resultCode == RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            paizhao.setImageBitmap(bitmap);
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_yiche_du_che;
    }
}