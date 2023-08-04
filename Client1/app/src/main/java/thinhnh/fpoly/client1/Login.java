package thinhnh.fpoly.client1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    private LinearLayout linearLayout;
    private TextView textView2;
    private EditText txtEdtMhloginUsername;
    private TextView textView3;
    private EditText txtEdtMhloginPass;
    private Spinner spinMhloginVaitro;
    private CheckBox chkLuumk;
    private TextView textView5;
    private LinearLayout linearLayout2;
    private ImageView imageView5;
    private ImageView imageView6;
    private ImageView imageView7;
    private AppCompatButton btnMhloginDangnhap;
    private AppCompatButton taotkAdmin;
    private TextView textView7;


    UserAmin MuserAmin;

    private     List<UserAmin> userAminList;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtEdtMhloginUsername = (EditText) findViewById(R.id.txt_edt_mhlogin_username);
        txtEdtMhloginPass = (EditText) findViewById(R.id.txt_edt_mhlogin_pass);
        spinMhloginVaitro = (Spinner) findViewById(R.id.spin_mhlogin_vaitro);
        chkLuumk = (CheckBox) findViewById(R.id.chk_luumk);
        btnMhloginDangnhap = (AppCompatButton) findViewById(R.id.btn_mhlogin_dangnhap);
        taotkAdmin = (AppCompatButton) findViewById(R.id.taotkAdmin);
        // Lấy mật khẩu
        sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        txtEdtMhloginPass.setText(sharedPreferences.getString("user_name",""));
        txtEdtMhloginPass.setText(sharedPreferences.getString("pass",""));
        chkLuumk.setChecked(sharedPreferences.getBoolean("ck",false));
        //set item cho spiner vai trò
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Vai_tro, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinMhloginVaitro.setAdapter(adapter);
        int pos = adapter.getPosition(sharedPreferences.getString("tk",""));
        spinMhloginVaitro.setSelection(pos);

        userAminList = new ArrayList<>();
        getListUserAmin();
        btnMhloginDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Login.this, "aabc", Toast.LENGTH_SHORT).show();
                clickLogin();
            }
        });
    }

    private void clickLogin() {
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
             Intent intent = new Intent( Login.this , ManHinhAdmin.class    );
             Bundle bundle = new Bundle();
             bundle.putSerializable("obj_userAdmin",MuserAmin);
            intent.putExtras(bundle);
            startActivity(intent);
         }else{
             Toast.makeText(Login.this, "sai tk hoặc mk", Toast.LENGTH_SHORT).show();
         }

    }

    private void getListUserAmin() {
        ApiLogin.apilogin.getListUserAmin().enqueue(new Callback<List<UserAmin>>() {
            @Override
            public void onResponse(Call<List<UserAmin>> call, Response<List<UserAmin>> response) {
userAminList = response.body();
                Log.e("zzzzzzzzzzzzzz",userAminList.size()+"");
            }

            @Override
            public void onFailure(Call<List<UserAmin>> call, Throwable t) {
                Toast.makeText(Login.this, "Call api erro", Toast.LENGTH_SHORT).show();
            }
        });
    }
}