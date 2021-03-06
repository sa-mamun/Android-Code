package demo.com.jsondataparsebyretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCilentInstance {

    private static final String BASE_URL = "https://api.openweathermap.org";
    private static Retrofit retrofit;


    static Retrofit getRetrofitInstance()
    {

        if(retrofit == null )
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        return retrofit;
    }

}
