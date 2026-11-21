package view;

import api.WeatherData;
import interface_adapter.weather_environment.WeatherController;
import interface_adapter.weather_environment.WeatherPresenter;
import interface_adapter.weather_environment.WeatherViewModel;
import use_case.weather_environment.WeatherDataInterface;
import use_case.weather_environment.WeatherInputBoundary;
import use_case.weather_environment.WeatherInteractor;
import use_case.weather_environment.WeatherOutputBoundary;

import javax.swing.*;
import java.awt.*;

public class WeatherViewDemo {
        public static void main(String[] args) {
            JFrame frame = new JFrame("Weather Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            WeatherViewModel  weatherViewModel = new WeatherViewModel();
            WeatherDataInterface weatherData = new WeatherData();
            WeatherOutputBoundary weatherPresenter = new WeatherPresenter(weatherViewModel);
            WeatherInputBoundary weatherInteractor = new WeatherInteractor(weatherData, weatherPresenter);
            WeatherController weatherController = new WeatherController(weatherInteractor);
            WeatherView weatherView = new WeatherView(weatherViewModel);
            weatherView.setWeatherController(weatherController);

            frame.setContentPane(weatherView);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
}

