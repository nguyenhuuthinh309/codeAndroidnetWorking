package thinhnh.fpoly.client1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thinhnh.fpoly.client1.API.InterfaceSelect;
import thinhnh.fpoly.client1.DTO.HD;
import thinhnh.fpoly.client1.Prd;
import thinhnh.fpoly.client1.R;
import thinhnh.fpoly.client1.Reponse.SvrResponseSelect;
import thinhnh.fpoly.client1.adapter.HoadonAdapter;

public class LichDatSan extends AppCompatActivity {
    private EditText lisEdttenhdtimkiem;
    private ImageView imgtimkiemten;
    private TextView sonv1;
    private RecyclerView lisCs;
    private FloatingActionButton floatCs;
    HoadonAdapter hoadonAdapter;
    List<HD> hoaDonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_dat_san);

        lisEdttenhdtimkiem = (EditText) findViewById(R.id.lis_edttenhdtimkiem);
        imgtimkiemten = (ImageView) findViewById(R.id.imgtimkiemten);
        sonv1 = (TextView) findViewById(R.id.sonv1);
//        lisCs = (RecyclerView) findViewById(R.id.lis_cs);
        floatCs = (FloatingActionButton) findViewById(R.id.float_cs);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        lisCs.setLayoutManager(linearLayoutManager);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
//        lisCs.addItemDecoration(dividerItemDecoration);
//
//
//        hoadonAdapter = new HoadonAdapter(hoaDonList);
//        lisCs.setAdapter(hoadonAdapter);
        selectData();


        floatCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichDatSan.this, ThemHoaDon.class);
                startActivity(intent);
            }
        });
    }
    String kq="";//chuoi ket qua
    ArrayList<Prd> ls;//tao list prd


    Prd prd=new Prd();//tao doi tuong prd
    void selectData(){
        //b1. tao doi tuong retrofit
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.16.102:3000/")
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
                ls=new ArrayList<>(Arrays.asList(svrResponseSelect.getHoaDon()));//chuyen du lieu sang list
                for(Prd p: ls)//cho vao vong for de doc tung doi tuong
                {
                    kq +="ID:"+p.getKhunggio()+"; Name: "+p.getTenkhach()+
                            "; Price: "+p.getTongtien()+
                            "; Des: "+p.getSdt()+"\n";
                }

                sonv1.setText(kq);
            }
            //that bai
            @Override
            public void onFailure(Call<SvrResponseSelect> call, Throwable t) {
                sonv1.setText(t.getMessage());//in ra thong bao loi
            }
        });
    }
}