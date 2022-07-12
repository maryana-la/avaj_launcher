package avaj_launcher.Flyable;

import avaj_launcher.Simulation.LogFile;
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
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                break;
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                break;
            case "SNOW":
            default:
                coordinates.setHeight(coordinates.getHeight() - 7);
                break;
        }
        LogFile.addToFile(getOutput() + ": " + weatherTower.getPhrase(weatherInfo));

        if(coordinates.getHeight() <= 0) {
            LogFile.addToFile(getOutput() + " landing");
            LogFile.addToFile(getOutput() + "latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude() + " height: " + coordinates.getHeight());
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