package thinhnh.fpoly.client1.API;
import retrofit2.Call;
import retrofit2.http.GET;
import thinhnh.fpoly.client1.Reponse.SvrResponseSelect;

public interface InterfaceSelect {
    @GET("product/select")
    Call<SvrResponseSelect> getPrd();
}

