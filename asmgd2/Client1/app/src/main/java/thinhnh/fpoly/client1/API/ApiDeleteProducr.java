package thinhnh.fpoly.client1.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import thinhnh.fpoly.client1.Reponse.DeleteProduct;

public interface ApiDeleteProducr {
    @FormUrlEncoded
    @POST("product/del/:id")
    Call<DeleteProduct> deleteProduct(@Field("id")int id);
}
