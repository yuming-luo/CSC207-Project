package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interface_adapter.weather_environment.WeatherViewModel;
import interface_adapter.weather_environment.WeatherState;
import interface_adapter.weather_environment.WeatherController;
import interface_adapter.weather_environment.WeatherViewModelListener;

public class WeatherView extends JPanel implements ActionListener, WeatherViewModelListener{
    private final WeatherViewModel weatherViewModel;

    private final JTextField cityInputField = new JTextField(15);
    private final JLabel cityErrorField = new JLabel();

    private final JButton select;
    private WeatherController weatherController = null;

    public WeatherView(WeatherViewModel weatherViewModel) {
        this.weatherViewModel = weatherViewModel;
        this.weatherViewModel.addListener(this);

        final JLabel title = new JLabel("Set Weather");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final WeatherLabelPanel cityName = new WeatherLabelPanel(
                new JLabel("City"), cityInputField);

        final JPanel buttons = new JPanel();
        select = new JButton("select");
        buttons.add(select);

        select.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(select)) {
                            final WeatherState currentState = weatherViewModel.getState();

                            weatherController.execute(currentState.getCity());
                        }
                    }
                }
        );

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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(cityName);
        this.add(cityErrorField);
        this.add(buttons);
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
