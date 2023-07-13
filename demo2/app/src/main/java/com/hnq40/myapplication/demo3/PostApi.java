package com.hnq40.myapplication.demo3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class PostApi extends AppCompatActivity {


     EditText editTextTextPersonName;
     Button button;
     TextView textView;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_api);

        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
new POSTAsyncTask().execute();
            }
        });
    }

    class POSTAsyncTask extends AsyncTask<Void, Void, Void> {
        String kq = "";
        String pathPost = "https://nguyenhuuthinh309.000webhostapp.com/androidNetwoerking/postAPI.php";

        @Override
        protected Void doInBackground(Void... voids) {
            try {
//1.chuyen path thanh url
URL url=new URL(pathPost);
//2. ma hoa tham so
 String param="canh="+ URLEncoder.encode(editTextTextPersonName.getText().toString(),"utf-8");
//3. Mo ket noi
 HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
//4. Thiet lap cac thuoc tinh cho ket noi
 urlConnection.setDoOutput(true);
 //lay ve ket qua
                urlConnection.setRequestMethod("POST");
                //xac dinh phuong thuc
                urlConnection.setFixedLengthStreamingMode(param.getBytes().length);
                //do dai tham so
                urlConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
//5. thiet lap tham so
PrintWriter printWriter=new PrintWriter(urlConnection.getOutputStream());
                printWriter.print(param);
                printWriter.close();
//6. doc du lieu
BufferedReader br=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    stringBuilder.append(line);
                }
//7. tra ve ket qua
kq=stringBuilder.toString();
                urlConnection.disconnect();
                //dong ket noi
                 } catch (MalformedURLException e) {
                e.printStackTrace();
                textView.setText(e.getMessage());
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();

            } catch (IOException e) {
                e.printStackTrace();

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            textView.setText(kq);//tra ket qua ve client
            }
        }

        }

