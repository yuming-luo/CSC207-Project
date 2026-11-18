package use_case.weather_environment;


public interface WeatherOutputBoundary {
    /**
     * Prepares the success view for the Weather Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(WeatherOutputData outputData);

}
