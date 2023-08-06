package thinhnh.fpoly.client1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thinhnh.fpoly.client1.API.ApiLogin;
import thinhnh.fpoly.client1.API.ApiSelectProduct;
import thinhnh.fpoly.client1.DTO.HD;
import thinhnh.fpoly.client1.DTO.UserAmin;
import thinhnh.fpoly.client1.R;
import thinhnh.fpoly.client1.adapter.HoadonAdapter;

public class LichDatSan extends AppCompatActivity {
    private EditText lisEdttenhdtimkiem;
    private ImageView imgtimkiemten;
    private TextView sonv1;
    private RecyclerView lisCs;
    private FloatingActionButton floatCs;
    HoadonAdapter hoadonAdapter;

    private     List<HD> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_dat_san);

        lisEdttenhdtimkiem = (EditText) findViewById(R.id.lis_edttenhdtimkiem);
        imgtimkiemten = (ImageView) findViewById(R.id.imgtimkiemten);
        sonv1 = (TextView) findViewById(R.id.sonv1);
       lisCs = (RecyclerView) findViewById(R.id.lis_cs);
        floatCs = (FloatingActionButton) findViewById(R.id.float_cs);
//

getListProduct();

        floatCs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LichDatSan.this, ThemHoaDon.class);
                startActivity(intent);
            }
        });
    }
    String kq="";//chuoi ket qua
    private void getListProduct(){
        ApiSelectProduct.apiselectpeoduct.getListProduct().enqueue(new Callback<List<HD>>() {
            @Override
            public void onResponse(Call<List<HD>> call, Response<List<HD>> response) {
                productList = response.body();

                for(HD p: productList)//cho vao vong for de doc tung doi tuong
                {


                    hoadonAdapter  = new HoadonAdapter(getBaseContext());
                    hoadonAdapter.setdata(productList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
                    lisCs.setLayoutManager(linearLayoutManager);
                    lisCs.setAdapter(hoadonAdapter);
                }



            }

            @Override
            public void onFailure(Call<List<HD>> call, Throwable t) {
                Toast.makeText(LichDatSan.this, "Call api erro", Toast.LENGTH_SHORT).show();
            }
        });
    }




}