package demo.com.jsondataparsebyretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Retrofit retrofit = RetrofitCilentInstance.getRetrofitInstance();
        DataServices dataServices = retrofit.create(DataServices.class);
        Call<DailyForecast> call = dataServices.getDailyForecast("Dhaka", "8fda6f98646be8ce133d784a36374fe9");

        call.enqueue(new Callback<DailyForecast>() {
            @Override
            public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {

                Log.e("OnResponse", "Heyyyyyyyyyyyyyy" );

                if (response.isSuccessful())
                {
                    List<Weather> list = response.body().getWeather();

                    Toast.makeText(MainActivity.this, list.get(0).getMain(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Something went wrong....please try again later.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DailyForecast> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
