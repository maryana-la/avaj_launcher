package avaj_launcher.Flyable;

import avaj_launcher.Simulation.InvalidCoordinatesException;
import avaj_launcher.Simulation.Simulator;
import avaj_launcher.Simulation.WeatherTower;

import java.util.Locale;

public class AircraftLauncher extends AircraftFactory {
    public Flyable createAircraft(String str, WeatherTower weatherTower) {
        String[] airInfo = str.split(" ");
        Flyable temp = null;
        try {
            if (airInfo.length != 5)
                throw new InvalidCoordinatesException("Wrong number of arguments");
            temp = newAircraft(airInfo[0].toUpperCase(Locale.ROOT), airInfo[1],
                    Integer.parseInt(airInfo[2]), Integer.parseInt(airInfo[3]), Integer.parseInt(airInfo[4]));
            temp.registerTower(weatherTower);
        } catch (InvalidCoordinatesException | NullPointerException e) {
            Simulator.printError(airInfo[0] + " " + airInfo[1] + " not created. " + e.getMessage(), 0);
        } catch (NumberFormatException e) {
            Simulator.printError(airInfo[0] + " " + airInfo[1] + " not created. Invalid integer. " + e.getMessage(), 0);
        } catch (Exception e) {
            Simulator.printError("Error creating an Aircraft. " + e.getMessage(), 0);
        }
        return temp;
    }
}
