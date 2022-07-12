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

    public String getPhrase(String weatherType) {
        switch (weatherType) {
            case "SUN":
                return "SUN: What a perfect day to enjoy a flight!";
            case "RAIN":
                return "RAIN:  It's raining. Better watch out for lightings.";
            case "FOG":
                return "FOG: I can't see anything around!";
            case "SNOW":
            default:
                return "SNOW: It's snowing. We should have stayed at home";
        }
    }
}