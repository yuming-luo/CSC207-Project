package use_case.weather_environment;


public interface WeatherOutputBoundary {
    /**
     * Prepares the success view for the Weather Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(WeatherOutputData outputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
