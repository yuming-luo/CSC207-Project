package interface_adapter.weather_environment;

import use_case.weather_environment.WeatherOutputBoundary;
import use_case.weather_environment.WeatherOutputData;

public class WeatherPresenter implements WeatherOutputBoundary {
    private final WeatherViewModel weatherViewModel;

    public WeatherPresenter( WeatherViewModel weatherViewModel ) {
        this.weatherViewModel = weatherViewModel;
    }

    @Override
    public void prepareSuccessView(WeatherOutputData response) {

        final WeatherState weatherState = weatherViewModel.getState();
        weatherState.setWeather(response.getWeather());
        weatherViewModel.notifyStateChanged();
    }

}
