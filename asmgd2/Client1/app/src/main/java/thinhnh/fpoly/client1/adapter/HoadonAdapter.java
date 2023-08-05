package thinhnh.fpoly.client1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import thinhnh.fpoly.client1.DTO.HD;
import thinhnh.fpoly.client1.R;

public class HoadonAdapter extends RecyclerView.Adapter<HoadonAdapter.UserViewHOlder> {
     List<HD> listhoadon;

    public HoadonAdapter(List<HD> listhoadon) {
        this.listhoadon = listhoadon;
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
