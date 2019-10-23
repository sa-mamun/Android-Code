package demo.com.jsondataparsebyretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCilentInstance {

    private static final String BASE_URL = "http://api.openweathermap.org/";
    private static Retrofit retrofit;


    static Retrofit getRetrofitInstance()
    {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
