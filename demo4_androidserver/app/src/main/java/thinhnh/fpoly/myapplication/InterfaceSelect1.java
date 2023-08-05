package thinhnh.fpoly.myapplication;
import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelect1 {
    @GET("product/select")
    Call<SvrResponseSelect1> getPrd();
}

