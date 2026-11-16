package interface_adapter.weather_environment;

import java.util.ArrayList;
import java.util.List;

public class WeatherViewModel {
    private final List<WeatherViewModelListener> listeners = new ArrayList<>();

    public static final String VIEW_NAME = "weather";

    private WeatherState state = new WeatherState();

    public WeatherState getState() {
        return this.state;
    }

    public void setState(WeatherState state) {
        this.state = state;
    }

    public void addListener(WeatherViewModelListener listener) {
        listeners.add(listener);
    }

    public void notifyStateChanged() {
        for (WeatherViewModelListener listener : listeners) {
            listener.stateChanged();
        }
    }
}
