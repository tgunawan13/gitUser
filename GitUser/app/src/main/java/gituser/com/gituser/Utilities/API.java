package gituser.com.gituser.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import gituser.com.gituser.Model.BaseResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("users")
    Call<BaseResponse> getResponse(@Query("q") String term,@Query("page") int page);

    class Factory {
        private static API service;

        public static API getInstance() {

            if (service == null) {
                Gson gson = new GsonBuilder()
                        .setDateFormat("")
                        .create();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.github.com/search/")
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build();

                service = retrofit.create(API.class);
                return service;
            } else {
                return service;
            }
        }
    }
}
