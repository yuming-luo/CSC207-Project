package api;
import entity.Weather;

public interface WeatherDataInterface {

    /**
     * A method that returns the current weather based on the city.
     * @return the current weather
     */
    Weather getCurrentWeather();

}
