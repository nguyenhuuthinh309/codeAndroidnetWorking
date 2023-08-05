package thinhnh.fpoly.client1.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thinhnh.fpoly.client1.API.ApiAddProduct;
import thinhnh.fpoly.client1.DTO.HD;
import thinhnh.fpoly.client1.R;
import thinhnh.fpoly.client1.Reponse.InsertProduct;

public class ThemHoaDon extends AppCompatActivity {
    //wexrdfgvhbuinuytvrerxctyvbun
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    int myear,mmonth,mday;



    private TextInputEditText tenkh;
    private TextInputEditText sdtkh;
    private TextView edtngaythue;
    private ImageView imgngay;
    private TextInputEditText spnkhunggio;
    private Button tongtien;
    private TextView texttongtien;
    private TextInputEditText spntrangthai1;
    private Button btnAddhdd;
    private Button btnHuyAddhdd;
    private ListView lisBT;
    ArrayList<HD> ls;//tao list prd
    HD prd=new HD();//tao doi tuong prd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoa_don);

        tenkh = (TextInputEditText) findViewById(R.id.tenkh);
        sdtkh = (TextInputEditText) findViewById(R.id.sdtkh);
        edtngaythue = (TextView) findViewById(R.id.edtngaythue);
        imgngay = (ImageView) findViewById(R.id.imgngay);
        spnkhunggio = (TextInputEditText) findViewById(R.id.spnkhunggio);
        tongtien = (Button) findViewById(R.id.tongtien);
        texttongtien = (TextView) findViewById(R.id.texttongtien);
        spntrangthai1 = (TextInputEditText) findViewById(R.id.spntrangthai1);
        btnAddhdd = (Button) findViewById(R.id.btnAddhdd);
        btnAddhdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });
        btnHuyAddhdd = (Button) findViewById(R.id.btnHuyAddhdd);
        lisBT = (ListView) findViewById(R.id.lis_BT);

    }

    void insertData()
    {
        //B0. Dua du lieu vao doi tuong
        prd.setTenkhach(tenkh.getText().toString());
        prd.setSdt(sdtkh.getText().toString());
        prd.setNgaythue(edtngaythue.getText().toString());
        prd.setKhunggio(spnkhunggio.getText().toString());
        prd.setTongtien(tongtien.getText().toString());
        prd.setTrangthai(spntrangthai1.getText().toString());
        //B1. Tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.16.102:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Chuan bi ham va thuc thi ham
        //2.1 - goi inteface
        ApiAddProduct interfaceInsert=retrofit.create(ApiAddProduct.class);
        //2.2. chuan bi ham
        Call<InsertProduct> call=
                interfaceInsert.insertProduct(prd.getTenkhach(),prd.getSdt(),prd.getNgaythue(),prd.getKhunggio(),prd.getTongtien(),prd.getTrangthai());
        //2.3 goi ham
   call.enqueue(new Callback<InsertProduct>() {
       @Override
       public void onResponse(Call<InsertProduct> call, Response<InsertProduct> response) {
           InsertProduct svrResponseUpdate = response.body();

           Toast.makeText(ThemHoaDon.this, "thêm mới thành công", Toast.LENGTH_SHORT).show();
           Intent intent = new Intent(
                   ThemHoaDon.this, LichDatSan.class);
           startActivity(intent);
       }

       @Override
       public void onFailure(Call<InsertProduct> call, Throwable t) {

       }
   });
    }
}