package thinhnh.fpoly.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelect {
    @GET("list")
    Call<SvrResponseSelect> getPrd();
}

