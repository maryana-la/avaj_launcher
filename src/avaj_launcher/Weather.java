package avaj_launcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public enum Weather {
    SUN,
    RAIN,
    FOG,
    SNOW;
}

abstract class Tower {
    private ArrayList<Flyable> observers;

    Tower() {
        observers = new ArrayList<>();
    }

    public void register(Flyable flyable) { this.observers.add(flyable); }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
    }

    protected void conditionsChanged() {
        for(int i = 0; i < observers.size(); i++ ) {
            observers.get(i).updateCondition();

            //todo correct removing ConcurrentModificationException
        }
    }
}

class WeatherTower extends Tower {
    public WeatherTower() {
        super();
    }

    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getProvider().getCurrentWeather(coordinates);
    }

    void changeWeather() { super.conditionsChanged(); }


}

/******************************************/

class WeatherProvider {
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