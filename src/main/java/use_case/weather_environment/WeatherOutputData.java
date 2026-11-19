package use_case.weather_environment;

import entity.Weather;

public class WeatherOutputData {
    private final Weather weather;

    public WeatherOutputData(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }


}
