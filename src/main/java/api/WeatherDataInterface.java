package api;
import entity.Weather;

public interface WeatherDataInterface {

    /**
     * A method that returns the current weather.
     * @return the current weather
     */
    Weather getCurrentWeather();

    /**
     * A method that sets weather given a location.
     * @param city is the city the user inputs.
     */
    void setCurrentWeather(String city);

    /**
     * A method that returns the image corresponding to the weather
     * @return the corresponding image of weather
     */
    String getWeatherImage();

}
