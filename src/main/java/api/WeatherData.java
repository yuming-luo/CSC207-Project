package api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Weather;
import use_case.weather_environment.WeatherDataInterface;

public class WeatherData implements WeatherDataInterface {
    private static final String API_URL = "https://api.weatherapi.com/v1/current.json";
    private static final String CONDITION = "condition";
    private static final String CONDITION_CODE = "code";
    private static final String IS_DAY = "is_day";
    private static  final String ERROR = "error";
    private static String API_KEY;

    //Get API key from env
    public static void setAPIKey() {
        API_KEY = System.getenv("key");
    }

    /**
     * Helper function to get the file name of image related to condition.
     * @param condition_code is the unique code of the weather condition.
     * @param is_day is the boolean indicator whether it is day or night.
     * @return a string which is the file name of the weather image or the default image if exception occurs.
     */
    public String getWeatherImage(int condition_code, int is_day) {
        String image = null;
        try {
            JSONArray jsonArray = new JSONArray(Files.readString(Paths.get("src/main/java/api/weather_conditions.json").toAbsolutePath()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                if (obj.getInt("code") == condition_code) {
                    if (is_day == 1) {
                        image = obj.getString("day_image");
                    } else {
                        image = obj.getString("night_image");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Image not found");
        }

        System.out.println(image);

        return image;
    }

    @Override
    public Weather getCurrentWeather(String city) {
        setAPIKey();

        // Build request to api
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("%s?key=%s&q=%s", API_URL, API_KEY, city))
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.has(ERROR)) {
                // According to api library, an error message will be returned if an error occurs
                throw new RuntimeException(responseBody.getString("message"));
            } else {
                // Get necessary values from response body
                final JSONObject currentWeather = responseBody.getJSONObject("current");
                final JSONObject condition = currentWeather.getJSONObject(CONDITION);
                final int condition_code = condition.getInt(CONDITION_CODE);
                final int is_day = currentWeather.getInt(IS_DAY);
                final String image = getWeatherImage(condition_code, is_day);

                // Construct and Return Weather object
                return new Weather(condition_code, condition.getString("text"), is_day, image);
            }

        }
        catch (IOException | JSONException event) {
            throw new RuntimeException(event);
        }
    }

}
