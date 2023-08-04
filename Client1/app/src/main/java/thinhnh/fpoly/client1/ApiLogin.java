package thinhnh.fpoly.client1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface ApiLogin {

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    ApiLogin apilogin = new Retrofit.Builder()
            .baseUrl("http://192.168.16.104:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiLogin.class);

    @GET("select")
    Call<List<UserAmin >> getListUserAmin ();
}
