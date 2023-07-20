package thinhnh.fpoly.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsert {
    @FormUrlEncoded
    @POST("create_product.php")
    Call<SvrResponseInsert> insertPrd(
            @Field("tensp") String tensp,
            @Field("giasp") String giasp,
            @Field("soluong") String soluong);
}