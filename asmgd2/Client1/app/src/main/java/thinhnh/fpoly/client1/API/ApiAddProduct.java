package thinhnh.fpoly.client1.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import thinhnh.fpoly.client1.Reponse.InsertProduct;
import thinhnh.fpoly.client1.Reponse.InsertUser;

public interface ApiAddProduct {
    @FormUrlEncoded
    @POST("product/insert")
    Call<InsertProduct> insertProduct(
            @Field("tenkhach") String tenkhach,
            @Field("sdt") String sdt,
            @Field("ngaythue") String ngaythue,
    @Field("khunggio") String khunggio,
    @Field("tongtien") String tongtien,
    @Field("trangthai") String trangthai);
}
