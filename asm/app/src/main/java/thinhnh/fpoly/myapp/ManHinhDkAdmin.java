package thinhnh.fpoly.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;



import thinhnh.fpoly.myapp.csdl.DAO.DAO_Admin;
import thinhnh.fpoly.myapp.csdl.DTO.Admin;
import thinhnh.fpoly.myapp.csdl.DTO.NhanVien;
import thinhnh.fpoly.myapp.csdl.data.DataBaSe;

public class ManHinhDkAdmin extends AppCompatActivity {
    Context context = this;

     EditText dkemail;
    EditText dkmatkhau;
    EditText dkrematkhau;
    EditText dkhoten;
    EditText dktensanbong;


     Button btnMhDkDangky, btnhuydk;
 Admin admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);


        dkemail =  findViewById(R.id.dkemail);
        dkmatkhau =  findViewById(R.id.dkmatkhau);
        dkrematkhau =  findViewById(R.id.dkrematkhau);
        dkhoten =  findViewById(R.id.dkhoten);
        dktensanbong =  findViewById(R.id.dktensanbong);

        btnMhDkDangky = (Button) findViewById(R.id.btn_mhDk_dangky);
        btnhuydk = findViewById(R.id.btn_huy_dangky);

        btnMhDkDangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertVollet();
            }
        });

        btnhuydk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });

    }
    private void insertVollet() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3. url
        String url="http://192.168.16.104:3000/apipostman/add";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Toast.makeText(context, response.toString()
                                , Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context,error.getMessage()
                        , Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("hoten",dkhoten.getText().toString());
                mydata.put("email",dkemail.getText().toString());
                mydata.put("matkhau",dkmatkhau.getText().toString());
                mydata.put("tensan",dktensanbong.getText().toString());

                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
}