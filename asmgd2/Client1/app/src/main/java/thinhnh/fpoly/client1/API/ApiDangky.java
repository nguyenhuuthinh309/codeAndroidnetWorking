package thinhnh.fpoly.client1.API;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import thinhnh.fpoly.client1.Reponse.InsertUser;

public interface ApiDangky {
    @FormUrlEncoded
    @POST("user/insert")
    Call<InsertUser> insertPrd(
            @Field("email") String email,
            @Field("mk") String mk,
            @Field("tensan") String tensan);
}
