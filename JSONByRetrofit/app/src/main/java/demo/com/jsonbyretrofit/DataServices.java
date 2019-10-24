package demo.com.jsonbyretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataServices {

    //https://api.openweathermap.org/data/2.5/forecast?q=Dhaka&cnt=7&appid=8fda6f98646be8ce133d784a36374fe9

    @GET("data/2.5/forecast")
    Call<DailyForecast> getDailyForecast(@Query("q") String city, @Query("cnt") int cnt, @Query("APPID") String apikey);

}
