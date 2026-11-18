package api;

import java.io.IOException;

import entity.Weather;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData implements WeatherDataInterface {
    private static final String API_URL = "https://api.weatherapi.com/v1/current.json";
    private static String CITY;
    private static String API_KEY;

    public WeatherData(String city) {
        CITY = city;
    }

    //Get API key from env
    public static void setAPIKey() {
        API_KEY = System.getenv("key");
    }

    public Weather getCurrentWeather() {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s?key=%s&q=%s", API_URL, API_KEY, CITY))
                .build();

        // Hint: look at the API documentation to understand what the response looks like.
        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());
            final JSONObject currentWeather = responseBody.getJSONObject("current");
            final JSONObject location = responseBody.getJSONObject("location");

            if (location.getString("name").equals(CITY)) {
                return new Weather(CITY, currentWeather.getJSONObject("condition").getString("text"),
                        currentWeather.getBoolean("is_day"));
            } else {
                throw new RuntimeException("City not found");
            }

        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

}
