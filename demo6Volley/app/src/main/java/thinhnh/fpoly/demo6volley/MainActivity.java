package thinhnh.fpoly.demo6volley;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    TextView tvKQ;
    Context context=this;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        tvKQ=findViewById(R.id.kq);
        btn1.setOnClickListener((view)->{
            getStringByVolley();
            Toast.makeText(context, "btn1", Toast.LENGTH_SHORT).show();
        });
        btn2.setOnClickListener((view)->{
            getJSON_ObjectsOfArray();
            Toast.makeText(context, "btn2", Toast.LENGTH_SHORT).show();
        });
    }
    String strKQ="";
    private void getJSON_ObjectsOfArray() {
        //0.Tao hang doi
        RequestQueue queue= Volley.newRequestQueue(context);
        //1.url
        String url="https://batdongsanabc.000webhostapp.com/mob403lab6/array_json_new.json";
        //2. Tao request -> (xac dinh Loai request)
        //Truong hop nay la ArrayRequest (vi day la mang cua cac doi tuong)
        //JsonArrayRequest(url,thanhCong,thatBai)
        JsonArrayRequest request=new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //ket qua tra ve MANG cua cac DOITUONG
                        //-> can dung vong For de doc het cac doi tuong
                        strKQ="";
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject person=response.getJSONObject(i);//lay doi tuong thu i
                                String id=person.getString("id");
                                String name=person.getString("name");
                                String email=person.getString("email");
                                JSONObject phone=person.getJSONObject("phone");
                                String mobile=phone.getString("mobile");
                                String home=phone.getString("home");
                                //Noi chuoi
                                strKQ += "id: "+id+"\n\n";
                                strKQ += "name: "+name+"\n\n";
                                strKQ += "email: "+email+"\n\n";
                                strKQ += "mobile: "+mobile+"\n\n";
                                strKQ += "home: "+home+"\n\n";
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //dua ket qua len man hinh
                        tvKQ.setText(strKQ);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        tvKQ.setText(error.getMessage());//in ra loi
                    }
                });
        //3.Truyen tham so (neu co)
        //b4.Xu ly request
        queue.add(request);

    }

    //doc chuoi tu trang google.com
    private void getStringByVolley() {
        //B0. Tao hang doi
        RequestQueue queue= Volley.newRequestQueue(context);
        //b1- Url
        String url="https://www.google.com/";
        //b2.Tao request
        //StringRequest(phuongThuc,url,thanhCong,thatbai)
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //dua ket qua len man hinh
                        tvKQ.setText("Ket qua: "+response.substring(0,1000));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());//in ra loi
            }
        });
        //b3-Xu ly request
        queue.add(stringRequest);

    }
}