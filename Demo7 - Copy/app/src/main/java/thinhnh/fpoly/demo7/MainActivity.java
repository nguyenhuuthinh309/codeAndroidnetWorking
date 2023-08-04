package thinhnh.fpoly.demo7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tvKQ;
     Button insert;
     Button delete;
     Button update;
     Button select;
    String strKQ="";
    Context context = this;
    EditText txt1,txt2,txt3,txt4,txt5;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        tvKQ = (TextView) findViewById(R.id.kq);
        insert = (Button) findViewById(R.id.insert);
        delete = (Button) findViewById(R.id.delete);
        update = (Button) findViewById(R.id.update);
        select = (Button) findViewById(R.id.select);
        txt1 = (EditText) findViewById(R.id.editTextTextPersonName);
        txt2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        txt3 = (EditText) findViewById(R.id.editTextTextPersonName3);
        txt4 = (EditText) findViewById(R.id.editTextTextPersonName4);
        txt5 = (EditText) findViewById(R.id.editTextTextPersonName5);

        tvKQ = (TextView) findViewById(R.id.kq);
        insert = (Button) findViewById(R.id.insert);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertVollet();
            }
        });
        delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteVolley();
            }
        });
        update = (Button) findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateVolley();
            }
        });
        select = (Button) findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectVollay();
            }
        });
    }

    private void selectVollay() {

        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3. url
        String url="http://192.168.16.104:3000/apipostman/list";
        //b4. Xac dinh loai request
        JsonObjectRequest request=new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray nhanviens=response.getJSONArray("data");
                    strKQ="";
                    for (int i=0;i<nhanviens.length();i++)
                    {
                        JSONObject product=nhanviens.getJSONObject(i);
                        String manv=product.getString("manv");
                        String id=product.getString("_id");
                        String tennv=product.getString("tennv");
                        String diemtb=product.getString("diemtb");
                        String anh=product.getString("anh");
                        strKQ +="id: "+id +"\n";
                        strKQ +="pid: "+manv +"\n";
                        strKQ +="name: "+tennv +"\n";
                        strKQ +="price: "+diemtb +"\n";
                        strKQ +="description: "+anh +"\n";
                    }
                    tvKQ.setText(strKQ);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        });
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void updateVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="http://192.168.16.104:3000/apipostman/edit/:id";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("_id", txt1.getText().toString());
                mydata.put("manv", txt2.getText().toString());
                mydata.put("tennv",txt3.getText().toString());
                mydata.put("diemtb",txt4.getText().toString());

                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void deleteVolley() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue= Volley.newRequestQueue(context);
        //b3. url
        String url="http://192.168.16.104:3000/apipostman/delete/:id";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request=new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("_id",txt1.getText().toString());
                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }

    private void insertVollet() {
        //b1. chuan bi du lieu
        //b2. Tao queue
        RequestQueue queue=Volley.newRequestQueue(context);
        //b3. url
        String url="http://192.168.16.104:3000/apipostman/add";
        //b4. Xac dinh loai request
        //StringRequest(method,url,thanhCong,thatBai){thamso};
        StringRequest request= new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvKQ.setText(response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvKQ.setText(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> mydata=new HashMap<>();
                mydata.put("manv",txt1.getText().toString());
                mydata.put("tennv",txt2.getText().toString());
                mydata.put("diemtb",txt3.getText().toString());
                mydata.put("anh",txt4.getText().toString());

                return mydata;
            }
        };
        //b5. truyen tham so (neu co)
        //b6. thuc thi
        queue.add(request);
    }
}