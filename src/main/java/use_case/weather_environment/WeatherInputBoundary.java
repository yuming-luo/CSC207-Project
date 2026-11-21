package use_case.weather_environment;

/**
 * Input Boundary for actions which are related to setting weather.
 */
public interface WeatherInputBoundary {

    /**
     * Executes the weather use case.
     * @param weatherInputData the input data
     */
    void execute(WeatherInputData weatherInputData);
}
