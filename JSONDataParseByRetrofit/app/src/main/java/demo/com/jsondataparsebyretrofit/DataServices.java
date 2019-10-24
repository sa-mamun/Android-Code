package demo.com.jsondataparsebyretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataServices {

    //End Url : data/2.5/forecast/daily?q=Dhaka&APPID=62f6de3f7c0803216a3a13bbe4ea9914&cnt=7

    //http://api.openweathermap.org/data/2.5/weather?q=Dhaka&APPID=8fda6f98646be8ce133d784a36374fe9

    @GET("data/2.5/weather")
    Call<DailyForecast> getDailyForecast(@Query("q") String city,@Query("APPID") String apikey);

}
