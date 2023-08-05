package thinhnh.fpoly.client1.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import thinhnh.fpoly.client1.DTO.UserAmin;

public interface ApiLogin {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiLogin apilogin = new Retrofit.Builder()
            .baseUrl("http://192.168.16.102:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLogin.class);

    @GET("user/select")
    Call<List<UserAmin>> getListUserAmin ();
}
