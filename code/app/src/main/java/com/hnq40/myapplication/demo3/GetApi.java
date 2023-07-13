package com.hnq40.myapplication.demo3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hnq40.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetApi extends AppCompatActivity {
    TextView kq;
    EditText toan;
    EditText van;
    Button diemtb;
    @Override
    /////eeee
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_api2);
        kq = (TextView) findViewById(R.id.kq);
        toan = (EditText) findViewById(R.id.toan);
        van = (EditText) findViewById(R.id.van);
        diemtb = (Button) findViewById(R.id.diemtb);
        diemtb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GETAsyncTask().execute();//goi asynctask }
            }
        });
    }


class  GETAsyncTask extends AsyncTask<Void,Void,Void> {
    // khai bao duong dan
    String ketqua;
    String path = "https://nguyenhuuthinh309.000webhostapp.com/androidNetwoerking/getAPI.php";
    @Override
    protected Void doInBackground(Void... voids) {
        // truyen tham so cho duowng dan
        path+="?toan="+toan.getText().toString()+"&van="+van.getText().toString();
        try {
            // bien duowng dan thanhf url
            URL url = new URL(path);
            // tao bo dem doc du lieu
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            // doc tung dung du lieu
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();// vung chuwa du lieu
            while ((line = br.readLine())!=null){
                stringBuilder.append(line); // đưa từng dòng dữ liệu vào vùng chứa dữ liệu
            }
            // truyen du lieu vao ket qua
            ketqua = stringBuilder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        kq.setText(ketqua);//tra ket qua ve client }
    }
}
}