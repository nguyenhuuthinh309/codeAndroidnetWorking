package thinhnh.fpoly.client1.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import thinhnh.fpoly.client1.API.ApiDeleteProducr;
import thinhnh.fpoly.client1.API.ApiSelectProduct;
import thinhnh.fpoly.client1.API.InteLoadData;
import thinhnh.fpoly.client1.DTO.HD;
import thinhnh.fpoly.client1.R;
import thinhnh.fpoly.client1.Reponse.DeleteProduct;
import thinhnh.fpoly.client1.activity.LichDatSan;
import thinhnh.fpoly.client1.activity.ThemHoaDon;

public class HoadonAdapter extends RecyclerView.Adapter<HoadonAdapter.UserViewHOlder> {
     List<HD> listhoadon;
     Context context;
    InteLoadData inteloadData;

    public HoadonAdapter(Context context) {
        this.context = context;
    }

    public HoadonAdapter(Context context, InteLoadData inteloadData) {
        this.context = context;
        this.inteloadData = inteloadData;
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
        holder.itemId.setText("Sdt:"+hoaDon.getId());
        holder.itemTenkh.setText("Họ Tên:"+hoaDon.getTenkhach());
        holder.itemSdtkh.setText("SĐT:"+hoaDon.getSdt());
        holder.itemNgayThue.setText("Ngày Thuê:"+hoaDon.getNgaythue());
        holder.itemKhungGio.setText("Khung Giờ :"+hoaDon.getKhunggio());
        holder.itemHdtongtien.setText("Tổng Tiền:"+hoaDon.getTongtien());
        holder.itemHdtrangthai.setText("Trạng Thái :"+hoaDon.getTrangthai());
        holder.itemxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want delete?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://192.168.16.102:3000/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        ApiDeleteProducr interfaceDelete = retrofit.create(ApiDeleteProducr.class);
                        Call<DeleteProduct> call = interfaceDelete.deleteProduct(hoaDon.getId());
                        call.enqueue(new Callback<DeleteProduct>() {
                            @Override
                            public void onResponse(Call<DeleteProduct> call, Response<DeleteProduct> response) {
                                DeleteProduct svrResponseDelete = response.body(); // lay kq tu serrverr
                                Toast.makeText(context, "xóa thành công "+svrResponseDelete.getMessage() , Toast.LENGTH_SHORT).show();
                              inteloadData.loadData();

                            }



                            @Override
                            public void onFailure(Call<DeleteProduct> call, Throwable t) {
                                Toast.makeText(context, "xóa thất bại "+t.getMessage() , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                builder.setNegativeButton("NO",null);
                builder.show();

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

        private TextView itemTenkh,itemId;
        private TextView itemSdtkh;
        private TextView itemHdtongtien;
        private TextView itemHdtrangthai,itemNgayThue,itemKhungGio;
        private ImageView itemsua;
        private ImageView itemxoa;



        public UserViewHOlder(@NonNull View itemView) {
            super(itemView);
            itemId = (TextView) itemView.findViewById(R.id.item_id);
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
