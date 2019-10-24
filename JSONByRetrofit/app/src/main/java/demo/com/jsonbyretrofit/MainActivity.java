package demo.com.jsonbyretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        DataServices dataServices = retrofit.create(DataServices.class);
        Call<DailyForecast> call = dataServices.getDailyForecast("Dhaka", 7, "8fda6f98646be8ce133d784a36374fe9");

        call.enqueue(new Callback<DailyForecast>() {
            @Override
            public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {

                if (response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, response.body().getList().get(0).getClouds().toString(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Something went wrong, please try again later", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<DailyForecast> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
