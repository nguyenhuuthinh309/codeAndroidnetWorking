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
    EditText txt0, txt1,txt2,txt3;
    Button btnInsert,btnGet,btnupdate, btndelete;
    TextView tvKQ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt0 = findViewById(R.id.id);
        txt1=findViewById(R.id.demo41Txt1);
        txt2=findViewById(R.id.demo41Txt2);
        txt3=findViewById(R.id.demo41Txt3);
        btnInsert=findViewById(R.id.demo41Btn1);
        btnGet=findViewById(R.id.demo41Btn2);
        tvKQ=findViewById(R.id.demo41TvKQ);
        btndelete=findViewById(R.id.btndelete);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }


        });
        btnupdate=findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kq="";
                selectData1();
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kq="";
                selectData1();
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
    ArrayList<HD> ls1;//tao list prd
    HD hoaDon = new HD();
    Prd prd=new Prd();//tao doi tuong prd
    private void updateData() {
        Prd p = new Prd();
        p.setIdsp(txt0.getText().toString());
        p.setTensp(txt1.getText().toString());
        p.setGiasp(txt2.getText().toString());
        p.setSoluong(txt3.getText().toString());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nguyenhuuthinh309.000webhostapp.com/demo4_serverandroid/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        InterfaceUpdate interfaceUpdate = retrofit.create(InterfaceUpdate.class);
        Call<SvrResponseUpdate> call = interfaceUpdate.updatedata(p.getIdsp(),p.getTensp(),p.getGiasp(),p.getSoluong());
        call.enqueue(new Callback<SvrResponseUpdate>() {
            @Override
            public void onResponse(Call<SvrResponseUpdate> call, Response<SvrResponseUpdate> response) {
                SvrResponseUpdate svrResponseUpdate = response.body();
                tvKQ.setText(svrResponseUpdate.getMessage());
            }

            @Override
            public void onFailure(Call<SvrResponseUpdate> call, Throwable t) {
           tvKQ.setText(t.getMessage());
            }
        });

    }

    private void deleteData() {
        Prd p = new Prd();
        p.setIdsp(txt0.getText().toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nguyenhuuthinh309.000webhostapp.com/demo4_serverandroid/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceDelete interfaceDelete = retrofit.create(InterfaceDelete.class);
        Call<SvrResponseDelete> call = interfaceDelete.delete(p.getIdsp());
        call.enqueue(new Callback<SvrResponseDelete>() {
            @Override
            public void onResponse(Call<SvrResponseDelete> call, Response<SvrResponseDelete> response) {
                SvrResponseDelete svrResponseDelete = response.body(); // lay kq tu serrverr
                tvKQ.setText((svrResponseDelete.getMessage()));// dua kq len man hinh
            }

            @Override
            public void onFailure(Call<SvrResponseDelete> call, Throwable t) {
           tvKQ.setText(t.getMessage()); // dua ra loi
            }
        });
    }


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

    void selectData1(){
        //b1. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(" http://192.168.1.7:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2. Goi interface, chuan bi ham va goi ham
        InterfaceSelect1 interfaceSelect=retrofit.create(InterfaceSelect1.class);
        Call<SvrResponseSelect1> call=interfaceSelect.getPrd();
        call.enqueue(new Callback<SvrResponseSelect1>() {
            //thanh cong
            @Override
            public void onResponse(Call<SvrResponseSelect1> call, Response<SvrResponseSelect1> response) {
                SvrResponseSelect1 svrResponseSelect=response.body();//lay ket qua server tra ve
                ls1=new ArrayList<>(Arrays.asList(svrResponseSelect.getHoaDon()));//chuyen du lieu sang list
                for(HD p: ls1)//cho vao vong for de doc tung doi tuong
                {
                    kq +="ID:"+p.getKhunggio()+"; Name: "+p.getNgaythue()+
                            "; Price: "+p.getTenkhach()
                            +
                            "; Des: "+p.getSdt()+"\n";
                }

                tvKQ.setText(kq);
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseSelect1> call, Throwable t) {
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
                    kq +="ID:"+p.getIdsp()+"; Name: "+p.getTensp()+
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