package use_case.weather_environment;

import entity.Weather;
import org.junit.jupiter.api.Test;

public class WeatherInteractorTest {
    WeatherDataInterface weatherDAO = new WeatherDataInterface() {
        @Override
        public Weather getCurrentWeather(String city) {
            try {
                String[] values = city.split("_");
                int code = Integer.parseInt(values[0]);
                String condition = values[1];
                int is_day = Integer.parseInt(values[2]);
                String image = values[3];
                return new Weather(code, condition, is_day, image);
            } catch (Exception e) { return null; }
        }
    };

    @Test
    public void testValidCity() {
        final String testCity = "100_Sunny_1_sunny.jpg";

        TestOutputBoundary outputBoundary = new TestOutputBoundary();

        WeatherInteractor weatherInteractor = new WeatherInteractor(weatherDAO, outputBoundary);
        WeatherInputData weatherInputData = new WeatherInputData(testCity);
        weatherInteractor.execute(weatherInputData);

        assert(outputBoundary.outputData.getWeather().getCode() == 100);
        assert(outputBoundary.outputData.getWeather().getCondition().equals("Sunny"));
        assert(outputBoundary.outputData.getWeather().getIs_Day() == 1);
        assert(outputBoundary.outputData.getWeather().getImage().equals("sunny.jpg"));
        
    }

    @Test
    public void testInvalidCity() {
        final String testInvalidCity = "notvalidcity";

        TestOutputBoundary outputBoundary = new TestOutputBoundary();

        WeatherInteractor weatherInteractor = new WeatherInteractor(weatherDAO, outputBoundary);
        WeatherInputData weatherInputData = new WeatherInputData(testInvalidCity);
        weatherInteractor.execute(weatherInputData);

        assert(outputBoundary.error.equals("Failed to get weather"));

    }

    private static class TestOutputBoundary implements WeatherOutputBoundary{
        private WeatherOutputData outputData;
        private String error;

        public void prepareSuccessView(WeatherOutputData outputData)  {
            this.outputData = outputData;
        }
        public void prepareFailView(String errorMessage) { this.error = errorMessage; }
    }

}
