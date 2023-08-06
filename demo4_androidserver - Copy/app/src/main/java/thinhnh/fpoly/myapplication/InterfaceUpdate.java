package thinhnh.fpoly.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceUpdate {
    @FormUrlEncoded
    @POST("update_product.php")
    Call<SvrResponseUpdate> updatedata(
            @Field("Idsp")String Idsp,
            @Field("tensp")String tensp,
            @Field("giasp")String giasp,
            @Field("soluong")String soluong
    );
}
