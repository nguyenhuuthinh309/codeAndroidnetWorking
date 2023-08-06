package thinhnh.fpoly.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceDelete {
    @FormUrlEncoded
    @POST("delete_product.php")
    Call<SvrResponseDelete> delete(@Field("Idsp")String Idsp);
}
