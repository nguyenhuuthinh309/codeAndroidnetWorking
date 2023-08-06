package thinhnh.fpoly.client1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thinhnh.fpoly.client1.API.ApiDangky;
import thinhnh.fpoly.client1.DTO.UserAmin;
import thinhnh.fpoly.client1.R;
import thinhnh.fpoly.client1.Reponse.InsertUser;

public class DangKy extends AppCompatActivity {
    private TextView textView;
    private LinearLayout linearLayout;
    private EditText dkhoten;
    private TextView textView2;
    private EditText dkemail;
    private TextView textView22;
    private EditText dkmatkhau;
    private TextView textView3;
    private EditText dktensanbong;
    private AppCompatButton btnMhDkDangky;
    private AppCompatButton btnHuyDangky;
    ArrayList<UserAmin> ls;//tao list prd
    UserAmin prd=new UserAmin();//tao doi tuong prd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        dkhoten = (EditText) findViewById(R.id.dkhoten);
        dkemail = (EditText) findViewById(R.id.dkemail);

        dkmatkhau = (EditText) findViewById(R.id.dkmatkhau);


        btnMhDkDangky = (AppCompatButton) findViewById(R.id.btn_mhDk_dangky);
        btnMhDkDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        btnHuyDangky = (AppCompatButton) findViewById(R.id.btn_huy_dangky);
        btnHuyDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKy.this, Login.class);
                startActivity(intent);
            }
        });

    }
    void insertData()
    {
        //B0. Dua du lieu vao doi tuong
        prd.setTensan(dkhoten.getText().toString());
        prd.setEmail(dkemail.getText().toString());
        prd.setMk(dkmatkhau.getText().toString());
        //B1. Tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.1.7:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Chuan bi ham va thuc thi ham
        //2.1 - goi inteface
        ApiDangky interfaceInsert=retrofit.create(ApiDangky.class);
        //2.2. chuan bi ham
        Call<InsertUser> call=
                interfaceInsert.insertPrd(prd.getEmail(),prd.getMk(),prd.getTensan());
        //2.3 goi ham

        call.enqueue(new Callback<InsertUser>() {
            @Override
            public void onResponse(Call<InsertUser> call, Response<InsertUser> response) {
                InsertUser svrResponseUpdate = response.body();

                Toast.makeText(DangKy.this, "đăng ký thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DangKy.this, Login.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<InsertUser> call, Throwable t) {

            }
        });

    }
}