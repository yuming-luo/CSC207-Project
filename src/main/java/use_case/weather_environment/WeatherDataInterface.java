package use_case.weather_environment;
import entity.Weather;

public interface WeatherDataInterface {

    /**
     * A method that returns the current weather based on the city.
     * @param city is the city that we want to check the current weather of.
     * @return the current weather.
     */
    Weather getCurrentWeather(String city);

}
