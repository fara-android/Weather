package kg.chinodev.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    TextView cityName;
    TextView tempText;
    TextView descrText;
    TextView windDirText;
    TextView windSpeed;
    TextView sunriseText;
    TextView sunsetText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityName = findViewById(R.id.city_name);
        tempText = findViewById(R.id.temp_text);
        descrText = findViewById(R.id.descr_text);
//        windDirText = findViewById(R.id.wind_dir_text);
        windSpeed = findViewById(R.id.speed_text);
        sunriseText = findViewById(R.id.sunrise_text);
        sunsetText = findViewById(R.id.sunset_text);

        NetworkHelper.getInstance()
                .getService()
                //добавляем наши значения параметров
                .getCurrentWeather("b96a6f3f18df40ae94a7c47fc96c4339", "ru", "Bishkek")
                .enqueue(new Callback<CurrentWeatherResponse>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {

                        if (response.isSuccessful()){
                            CurrentWeatherItem item = response.body().getData().get(0);
                            cityName.setText(item.getCityName());
                            tempText.setText(""+ item.getTemp());
                            descrText.setText(item.getWeather().getDescription());
//                            windDirText.setText(item.getWindDir());
                            windSpeed.setText(""+item.getWindSpeed());
                            sunriseText.setText(item.getSunrise());
                            sunsetText.setText(item.getSunset());

                        }
                        else {
                            cityName.setText("mistake");
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {

                    }
                });
    }
}