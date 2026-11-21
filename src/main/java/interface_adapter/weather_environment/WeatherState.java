package interface_adapter.weather_environment;

import entity.Weather;

public class WeatherState {
    private String city;
    private String cityError;
    private Weather weather;
    private String weatherError;

    public String getCity() { return city; }

    public void setCity(String city) {
        this.city = city;
    }

    public Weather getWeather() {
        return weather;
    }

    public String getWeatherError() {
        return weatherError;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
    public void setWeatherError(String weatherError) {
        this.weatherError = weatherError;
    }

}
