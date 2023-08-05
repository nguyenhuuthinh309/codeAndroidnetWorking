package thinhnh.fpoly.client1.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import thinhnh.fpoly.client1.API.ApiLogin;
import thinhnh.fpoly.client1.DTO.UserAmin;
import thinhnh.fpoly.client1.ManHinhAdmin;
import thinhnh.fpoly.client1.R;

public class Login extends AppCompatActivity {

    private EditText txtEdtMhloginUsername;

    private EditText txtEdtMhloginPass;
    private Spinner spinMhloginVaitro;
    private CheckBox chkLuumk;

    private AppCompatButton btnMhloginDangnhap;
    private AppCompatButton taotkAdmin;



    UserAmin MuserAmin;

    private     List<UserAmin> userAminList;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtEdtMhloginUsername = (EditText) findViewById(R.id.txt_edt_mhlogin_username);
        txtEdtMhloginPass = (EditText) findViewById(R.id.txt_edt_mhlogin_pass);

        chkLuumk = (CheckBox) findViewById(R.id.chk_luumk);
        btnMhloginDangnhap = (AppCompatButton) findViewById(R.id.btn_mhlogin_dangnhap);
        taotkAdmin = (AppCompatButton) findViewById(R.id.taotkAdmin);
        // Lấy mật khẩu
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        txtEdtMhloginPass.setText(sharedPreferences.getString("user_name",""));
        txtEdtMhloginPass.setText(sharedPreferences.getString("pass",""));
        chkLuumk.setChecked(sharedPreferences.getBoolean("ck",false));
        //set item cho spiner vai trò


        userAminList = new ArrayList<>();
        getListUserAmin();
        btnMhloginDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txtEdtMhloginUsername.getText().toString().trim();
                String mk = txtEdtMhloginPass.getText().toString().trim();

                if(userAminList == null || userAminList.isEmpty()){
                    return;
                }
                boolean ishaduser = false;
                for (UserAmin userAmin : userAminList){
                    if(email.equals(userAmin.getEmail()) && mk.equals(userAmin.getMk())){
                        ishaduser = true;
                        MuserAmin = userAmin;
                        break;
                    }
                }
                if (ishaduser){
                    Intent intent = new Intent(Login.this , ManHinhAdmin.class);
                    Bundle bundle = new Bundle();

                    bundle.putSerializable("obj_userAdmin",MuserAmin);
                    intent.putExtras(bundle);
                    Toast.makeText(Login.this, "VAO", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this, "sai tk hoặc mk", Toast.LENGTH_SHORT).show();
                }
            }
        });
        taotkAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, DangKy.class);
                startActivity(intent);
            }
        });
    }


    private void getListUserAmin() {
        ApiLogin.apilogin.getListUserAmin().enqueue(new Callback<List<UserAmin>>() {
            @Override
            public void onResponse(Call<List<UserAmin>> call, Response<List<UserAmin>> response) {
userAminList = response.body();
                Toast.makeText(Login.this, "Call api thanh cong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<UserAmin>> call, Throwable t) {
                Toast.makeText(Login.this, "Call api erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}