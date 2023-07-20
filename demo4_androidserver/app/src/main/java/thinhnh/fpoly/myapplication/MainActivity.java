package thinhnh.fpoly.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import retrofit2.Callback;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText txt1,txt2,txt3;
    Button btnInsert,btnGet;
    TextView tvKQ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=findViewById(R.id.demo41Txt1);
        txt2=findViewById(R.id.demo41Txt2);
        txt3=findViewById(R.id.demo41Txt3);
        btnInsert=findViewById(R.id.demo41Btn1);
        btnGet=findViewById(R.id.demo41Btn2);
        tvKQ=findViewById(R.id.demo41TvKQ);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectData();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });

    }
    String kq="";//chuoi ket qua
    ArrayList<Prd> ls;//tao list prd
    Prd prd=new Prd();//tao doi tuong prd
    void insertData()
    {
        //B0. Dua du lieu vao doi tuong
        prd.setTensp(txt1.getText().toString());
        prd.setGiasp(txt2.getText().toString());
        prd.setSoluong(txt3.getText().toString());
        //B1. Tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://nguyenhuuthinh309.000webhostapp.com/demo4_serverandroid/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Chuan bi ham va thuc thi ham
        //2.1 - goi inteface
        InterfaceInsert interfaceInsert=retrofit.create(InterfaceInsert.class);
        //2.2. chuan bi ham
        Call<SvrResponseInsert> call=
                interfaceInsert.insertPrd(prd.getTensp(),prd.getGiasp(),prd.getSoluong());
        //2.3 goi ham
        call.enqueue(new Callback<SvrResponseInsert>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseInsert> call, Response<SvrResponseInsert> response) {
                SvrResponseInsert svrResponseInsert=response.body();//lay noi dung server tra ve
                tvKQ.setText(svrResponseInsert.getMessage());//in ra ket qua
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseInsert> call, Throwable t) {
                tvKQ.setText(t.getMessage());//in ra thong bao loi
            }
        });
    }
    void selectData(){
        //b1. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://nguyenhuuthinh309.000webhostapp.com/demo4_serverandroid/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface, chuan bi ham va goi ham
        InterfaceSelect interfaceSelect=retrofit.create(InterfaceSelect.class);
        Call<SvrResponseSelect> call=interfaceSelect.getPrd();
        call.enqueue(new Callback<SvrResponseSelect>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseSelect> call, Response<SvrResponseSelect> response) {
                SvrResponseSelect svrResponseSelect=response.body();//lay ket qua server tra ve
                ls=new ArrayList<>(Arrays.asList(svrResponseSelect.getBangTest()));//chuyen du lieu sang list
                for(Prd p: ls)//cho vao vong for de doc tung doi tuong
                {
                    kq +="Name: "+p.getTensp()+
                            "; Price: "+p.getGiasp()+
                            "; Des: "+p.getSoluong()+"\n";
                }
                tvKQ.setText(kq);
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable t) {
                tvKQ.setText(t.getMessage());//in ra thong bao loi
            }
        });
    }
}