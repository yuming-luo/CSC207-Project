package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import api.WeatherData;
import entity.Weather;
import interface_adapter.weather_environment.*;
import use_case.weather_environment.WeatherDataInterface;
import use_case.weather_environment.WeatherInputBoundary;
import use_case.weather_environment.WeatherInteractor;
import use_case.weather_environment.WeatherOutputBoundary;

public class WeatherView extends JLayeredPane implements ActionListener, WeatherViewModelListener{
    private final WeatherViewModel weatherViewModel;

    private final JTextField cityInputField = new JTextField(15);
    private final JLabel cityErrorField = new JLabel();

    private final JButton select;
    private WeatherController weatherController = null;

    private String weatherImage = "night.jpg";

    public WeatherView(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
        this.weatherViewModel.addListener(this);

        this.setPreferredSize(new Dimension(400, 400));

        // Create label for background image
        JLabel backgroundImage = new JLabel();
        backgroundImage.setIcon(new ImageIcon("images/" + weatherImage));

        backgroundImage.setBounds(0, 0, 400, 400);

        // Create a panel for all foreground components
        JPanel foreground = new JPanel();

        final JLabel title = new JLabel("Set Weather");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final WeatherLabelPanel cityName = new WeatherLabelPanel(
                new JLabel("City"), cityInputField);
        cityName.setOpaque(false);

        final JPanel buttons = new JPanel();
        select = new JButton("select");
        buttons.add(select);
        buttons.setOpaque(false);

        cityInputField.getDocument().addDocumentListener(new DocumentListener() {

            private void documentListenerHelper() {
                final WeatherState currentState = weatherViewModel.getState();
                currentState.setCity(cityInputField.getText());
                weatherViewModel.setState(currentState);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                documentListenerHelper();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                documentListenerHelper();
            }
        });

        select.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(select)) {
                            final WeatherState currentState = weatherViewModel.getState();

                            weatherController.execute(currentState.getCity());
                            Weather weather = currentState.getWeather();
                            if (weather != null) {
                                weatherImage = weather.getImage();
                                System.out.println(weatherImage);
                                backgroundImage.setIcon(new ImageIcon("images/" + weatherImage));
                            }
                        }
                    }
                }
        );

        foreground.setLayout(new BoxLayout(foreground, BoxLayout.Y_AXIS));

        foreground.add(title);
        foreground.add(cityName);
        foreground.add(cityErrorField);
        foreground.add(buttons);

        foreground.setBounds(0, 0, 400, 400);
        foreground.setOpaque(false);

        // layer the foreground onto the background
        this.add(backgroundImage, Integer.valueOf(0));
        this.add(foreground, Integer.valueOf(1));

    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void stateChanged() {
        setFields(weatherViewModel.getState());
        cityErrorField.setText(weatherViewModel.getState().getWeatherError());
    }

    private void setFields(WeatherState state) {
        cityInputField.setText(state.getCity());
    }

    public void setWeatherController(WeatherController weatherController) {
        this.weatherController = weatherController;
    }
}
