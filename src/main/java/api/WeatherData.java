package api;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Weather;
import use_case.weather_environment.WeatherDataInterface;

public class WeatherData implements WeatherDataInterface {
    private static final String API_URL = "https://api.weatherapi.com/v1/current.json";
    private static final String CITY = "name";
    private static final String CONDITION = "condition";
    private static final String IS_DAY = "is_day";
    private static  final String ERROR = "error";
    private static String API_KEY;

    //Get API key from env
    public static void setAPIKey() {
        API_KEY = System.getenv("key");
    }

    public Weather getCurrentWeather(String city) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s?key=%s&q=%s", API_URL, API_KEY, city))
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.has(ERROR)) {
                throw new RuntimeException(responseBody.getString("message"));
            } else {
                final JSONObject currentWeather = responseBody.getJSONObject("current");
                final JSONObject location = responseBody.getJSONObject("location");

                return new Weather(location.getString(CITY), currentWeather.getJSONObject(CONDITION).getString("text"),
                        currentWeather.getBoolean(IS_DAY));
            }

        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

}
