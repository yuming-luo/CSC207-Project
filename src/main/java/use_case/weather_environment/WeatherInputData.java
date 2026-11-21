package use_case.weather_environment;

public class WeatherInputData {
    private final String city;

    public WeatherInputData(String city) {
        this.city = city;
    }

    public String getCity() { return this.city; }
}

