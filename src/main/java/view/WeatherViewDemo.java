package view;

import interface_adapter.weather_environment.WeatherViewModel;

import javax.swing.*;

public class WeatherViewDemo {
    public static void main(String[] args) {
        final JPanel cardPanel = new JPanel();
        WeatherViewModel weatherViewModel;
        WeatherView weatherView;

        JFrame application = new JFrame("Set Weather Example");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

//        viewManagerModel.setState(SignupViewModel.VIEW_NAME);
//        viewManagerModel.notifyViewChanged();

        weatherViewModel = new WeatherViewModel();
        weatherView = new WeatherView(weatherViewModel);
        cardPanel.add(weatherView, weatherViewModel.VIEW_NAME);

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);

    }
}
