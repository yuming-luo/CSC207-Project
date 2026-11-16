package api;

import java.io.IOException;

import entity.Weather;

public class WeatherData implements WeatherDataInterface {
    private String city;
    private Weather weather;
    private static String KEY;

    //Get API key from env
    public static String getAPIKey() {
        return System.getenv(KEY);
    }

    public Weather getCurrentWeather() { return this.weather; }

    public void setCurrentWeather(String city){

        https://api.weatherapi.com/v1/current.json?key=  &q=
    }

}
