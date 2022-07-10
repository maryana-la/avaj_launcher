package avaj_launcher.Simulation;

import avaj_launcher.Flyable.Coordinates;
import avaj_launcher.Weather.Tower;
import avaj_launcher.Weather.WeatherProvider;

public class WeatherTower extends Tower {
    public WeatherTower() {
        super();
    }

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() { super.conditionsChanged(); }
}