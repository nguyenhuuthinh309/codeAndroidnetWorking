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

        btnupdate=findViewById(R.id.btnupdate);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                kq="";
                selectData();
            }
        });


    }
    String kq="";//chuoi ket qua
    ArrayList<Prd> ls;//tao list prd
    Prd prd=new Prd();//tao doi tuong prd

    void selectData(){
        //b1. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.24.18.51:3000/apipostman/")
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
                ls=new ArrayList<>(Arrays.asList(svrResponseSelect.getNhanviens()));//chuyen du lieu sang list
                for(Prd p: ls)//cho vao vong for de doc tung doi tuong
                {
                    kq +="mã nhân viên:"+p.getManv()+"==== tên nhân viên : "+p.getTennv()+
                            "======== điểm tb: "+p.getDiemtb()+
                            "======= ảnh : "+p.getAnh()+"\n";
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