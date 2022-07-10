package avaj_launcher.Flyable;

import avaj_launcher.Simulation.Simulator;
import avaj_launcher.Simulation.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weatherInfo = weatherTower.getWeather(coordinates);
        switch (weatherInfo) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                break;
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                break;
            case "SNOW":
            default:
                coordinates.setHeight(coordinates.getHeight() - 15);
                break;
        }
        Simulator.addToFile(getOutput() + ": " + weatherInfo);

        if(coordinates.getHeight() <= 0) {
            Simulator.addToFile(getOutput() + " landing");
            weatherTower.unregister(this);
            Simulator.addToFile("Tower says: " + getOutput() + " unregistered from weather tower.");
            Simulator.addToFile("latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude() + " height: " + coordinates.getHeight());
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        if (this.coordinates.getHeight() > 0) {
            this.weatherTower.register(this);
            Simulator.addToFile("Tower says: " + getOutput() + " registered to weather tower.");
        }
    }
}
