package interface_adapter.weather_environment;

import use_case.weather_environment.WeatherInputBoundary;
import use_case.weather_environment.WeatherInputData;

/**
 * The controller for the Weather Use Case.
 */
public class WeatherController {
    private final WeatherInputBoundary weatherUseCaseInteractor;

    public WeatherController(WeatherInputBoundary weatherUseCaseInteractor) {
        this.weatherUseCaseInteractor = weatherUseCaseInteractor;
    }

    /**
     * Executes the Weather Use Case.
     * @param city the city selected by user
     */
    public void execute(String city) {
        final WeatherInputData weatherInputData = new WeatherInputData(city);

        weatherUseCaseInteractor.execute(weatherInputData);
    }
}
