package avaj_launcher.Weather;

import avaj_launcher.Flyable.Coordinates;

import java.util.Random;

public class WeatherProvider {
    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int num = new Random().nextInt(coordinates.getLatitude() * 4 + coordinates.getLongitude() * 3 + coordinates.getHeight() * 2) % 4;
        return weather[num];
    }
}