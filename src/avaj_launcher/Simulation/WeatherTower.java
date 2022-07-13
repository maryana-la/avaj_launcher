package avaj_launcher.Simulation;

import avaj_launcher.Flyable.Coordinates;
import avaj_launcher.Weather.Tower;
import avaj_launcher.Weather.WeatherProvider;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

public class WeatherTower extends Tower {
    private final static String[] SUN_STATUS = {
            "SUN: What a perfect day to enjoy a flight!",
            "SUN: Let's find a beach to grab som sun!",
            "SUN: OMG It's so hot! I'm melting",
            "SUN: The sky is so clear!" };

    private final static String[] RAIN_STATUS = {
            "RAIN: Better watch out for lightnings.",
            "RAIN: I didn't plan to get wet.",
            "RAIN: Damn you rain!",
            "RAIN: The sky is crying." };

    private final static String[] FOG_STATUS = {
            "FOG: Probably should go down.",
            "FOG: I can't see anything around!",
            "FOG: Don't see any problem.",
            "FOG: Hope I don't get lost" };

    private final static String[] SNOW_STATUS = {
            "SNOW: It's so freezing! Brrrrr....",
            "SNOW: It's snowing. We should have stayed at home",
            "SNOW: Already winter? Should buy presents for Christmas",
            "SNOW: Let it snow, let it snow" };

    public WeatherTower() { super(); }

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() { super.conditionsChanged(); }

    public String getPhrase(String weatherType) {
        switch (weatherType) {
            case "SUN":
                return SUN_STATUS[new Random().nextInt(4)];
            case "RAIN":
                return RAIN_STATUS[new Random().nextInt(4)];
            case "FOG":
                return FOG_STATUS[new Random().nextInt(4)];
            case "SNOW":
                return SNOW_STATUS[new Random().nextInt(4)];
            default:
                return "?!?!?";
        }
    }
}