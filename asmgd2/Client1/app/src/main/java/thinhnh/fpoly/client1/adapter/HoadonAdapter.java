package thinhnh.fpoly.client1.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thinhnh.fpoly.client1.API.ApiDeleteProducr;
import thinhnh.fpoly.client1.DTO.HD;
import thinhnh.fpoly.client1.R;
import thinhnh.fpoly.client1.Reponse.DeleteProduct;

public class HoadonAdapter extends RecyclerView.Adapter<HoadonAdapter.UserViewHOlder> {
     List<HD> listhoadon;
    private Context context;

    public HoadonAdapter(Context context) {
        this.context = context;
    }


    public void setdata(List<HD> listhoadon) {
        this.listhoadon = listhoadon;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public UserViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHOlder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHOlder holder, int position) {
        HD hoaDon = listhoadon.get(position);
        if (hoaDon == null){
            return;
        }
        holder.itemTenkh.setText("Họ Tên:"+hoaDon.getTenkhach());
        holder.itemSdtkh.setText("SĐT:"+hoaDon.getSdt());
        holder.itemNgayThue.setText("Ngày Thuê:"+hoaDon.getNgaythue());
        holder.itemKhungGio.setText("Khung Giờ :"+hoaDon.getKhunggio());
        holder.itemHdtongtien.setText("Tổng Tiền:"+hoaDon.getTongtien());
        holder.itemHdtrangthai.setText("Trạng Thái :"+hoaDon.getTrangthai());
        holder.itemxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialogEdit = new Dialog(context);
                dialogEdit.setContentView(R.layout.dialogxoa);
                EditText     editTextTextPersonName = (EditText) dialogEdit.findViewById(R.id.editTextTextPersonName);
                TextView   textView = (TextView) dialogEdit.findViewById(R.id.textView);
                Button btnEditHV = dialogEdit.findViewById(R.id.btnedit_HV);
                Button btnHuyEditHV = dialogEdit.findViewById(R.id.btnHuyEditHV);
                btnEditHV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        HD p = new HD();
                        p.setId(Integer.parseInt(editTextTextPersonName.getText().toString()));
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.1.7:3000/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ApiDeleteProducr interfaceDelete = retrofit.create(ApiDeleteProducr.class);
                        Call<DeleteProduct> call = interfaceDelete.deleteProduct(String.valueOf(p.getId()));
                        call.enqueue(new Callback<DeleteProduct>() {
                            @Override
                            public void onResponse(Call<DeleteProduct> call, Response<DeleteProduct> response) {
                                DeleteProduct svrResponseDelete = response.body(); // lay kq tu serrverr
                                Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<DeleteProduct> call, Throwable t) {
                                Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialogEdit.dismiss();
                    }
                });
                btnHuyEditHV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogEdit.dismiss();
                    }
                });
                dialogEdit.show();

            }
        });


    }

    @Override
    public int getItemCount() {
        if (listhoadon != null){
            return listhoadon.size();
        }
        return 0;
    }

    public  class  UserViewHOlder extends RecyclerView.ViewHolder{

        private TextView itemTenkh;
        private TextView itemSdtkh;
        private TextView itemHdtongtien;
        private TextView itemHdtrangthai,itemNgayThue,itemKhungGio;
        private ImageView itemsua;
        private ImageView itemxoa;



        public UserViewHOlder(@NonNull View itemView) {
            super(itemView);
            itemTenkh = (TextView) itemView.findViewById(R.id.item_tenkh);
            itemSdtkh = (TextView) itemView.findViewById(R.id.item_sdtkh);
            itemKhungGio = (TextView) itemView.findViewById(R.id.item_gio);
            itemNgayThue = (TextView) itemView.findViewById(R.id.item_ngaythue);
            itemHdtongtien = (TextView) itemView.findViewById(R.id.item_hdtongtien);
            itemHdtrangthai = (TextView) itemView.findViewById(R.id.item_hdtrangthai);
            itemsua = (ImageView) itemView.findViewById(R.id.itemsua);
            itemxoa = (ImageView) itemView.findViewById(R.id.itemxoa);
        }
    }
}
