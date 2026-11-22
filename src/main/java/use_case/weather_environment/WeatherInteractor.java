package use_case.weather_environment;

import entity.Weather;

public class WeatherInteractor implements WeatherInputBoundary {
    private final WeatherDataInterface weatherData;
    private final WeatherOutputBoundary weatherPresenter;

    public WeatherInteractor(WeatherDataInterface weatherData,
                           WeatherOutputBoundary weatherOutputBoundary) {
        this.weatherData = weatherData;
        this.weatherPresenter = weatherOutputBoundary;
    }

    @Override
    public void execute(WeatherInputData weatherInputData) {
        final String city = weatherInputData.getCity();
        final Weather weather = weatherData.getCurrentWeather(city);

        if (weather == null) {
            weatherPresenter.prepareFailView("Failed to get weather");
        } else {
            final WeatherOutputData weatherOutputData = new WeatherOutputData(weather);
            weatherPresenter.prepareSuccessView(weatherOutputData);
        }

    }

}