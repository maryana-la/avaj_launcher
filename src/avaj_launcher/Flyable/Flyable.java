package avaj_launcher.Flyable;

import avaj_launcher.Simulation.WeatherTower;

public interface Flyable {
    public void updateCondition();
    public void registerTower(WeatherTower tower);
}
