package avaj_launcher.Flyable;

import avaj_launcher.Utils.LogFile;
import avaj_launcher.Simulation.WeatherTower;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weatherInfo = weatherTower.getWeather(coordinates);
        switch (weatherInfo) {
            case "SUN":
                coordinates.updateCoordinates(0, 10, 2);
                break;
            case "RAIN":
                coordinates.updateCoordinates(0, 5, 0);
                break;
            case "FOG":
                coordinates.updateCoordinates(0, 1, 0);
                break;
            case "SNOW":
            default:
                coordinates.updateCoordinates(0, 0, -7);
                break;
        }
        LogFile.addToFile(getOutput() + ": " + weatherTower.getPhrase(weatherInfo));

        if(coordinates.getHeight() <= 0) {
            LogFile.addToFile(getOutput() + " landing");
            LogFile.addToFile(getOutput() + coordinates.outputCoordinates());
            weatherTower.unregister(this);
            LogFile.addToFile("Tower says: " + getOutput() + " unregistered from weather tower.");
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        if (this.coordinates.getHeight() > 0) {
            this.weatherTower.register(this);
            LogFile.addToFile("Tower says: " + getOutput() + " registered to weather tower.");
        }
    }
}