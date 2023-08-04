package thinhnh.fpoly.client1.ACtyviti;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import thinhnh.fpoly.myapp.R;
import thinhnh.fpoly.myapp.csdl.DTO.Admin;
import thinhnh.fpoly.myapp.csdl.DTO.NhanVien;

public class ThongTin extends AppCompatActivity {
    TextView txtname;
    TextView txtsdt;
    NhanVien nhanVien;
    Admin admin;
    Button doimk, dangxuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thong_tin);

        txtname = (TextView) findViewById(R.id.txtname);
        txtsdt = (TextView)findViewById(R.id.txtsdt);
        doimk =  findViewById(R.id.mDoiMatKhau);
        dangxuat =findViewById(R.id.dangxuat);


        Bundle bundle = getIntent().getExtras();
        String permission = bundle.getString("value");
        if(permission.equalsIgnoreCase("ADMIN")){

            txtname.setText(getIntent().getStringExtra("tenHV"));
            txtsdt.setText(getIntent().getStringExtra("userHV"));

        }else if(permission.equalsIgnoreCase("Nhân Viên")){


        }
//





    }
}