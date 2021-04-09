package kg.chinodev.weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v2.0/current") //ендпоинт текущей погоды
    public Call<CurrentWeatherResponse> getCurrentWeather(
            @Query("key") String key, // обязательный параметр ключа. без него не вернет погоду
            @Query("lang") String lang, // параметр языка данных ответа (ru, en ...)
            @Query("city") String city // параметр города (напр. Bishkek)
    );
}
