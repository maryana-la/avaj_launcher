package avaj_launcher.Flyable;

import avaj_launcher.Simulation.InvalidCoordinatesException;
import avaj_launcher.Simulation.WeatherTower;

import java.util.Locale;

public class AircraftLauncher extends AircraftFactory {
    public Flyable createAircraft(String str, WeatherTower weatherTower) {
        String[] airInfo = str.split(" ");
        Flyable temp = null;
        try {
            temp = newAircraft(airInfo[0].toUpperCase(Locale.ROOT), airInfo[1],
                    Integer.parseInt(airInfo[2]), Integer.parseInt(airInfo[3]), Integer.parseInt(airInfo[4]));
            temp.registerTower(weatherTower);
        } catch (InvalidCoordinatesException | NullPointerException e) {
            System.out.println(airInfo[0] + " " + airInfo[1] + " not created. " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println(airInfo[0] + " " + airInfo[1] + " not created. Invalid integer. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error creating an Aircraft. " + e.getMessage());
        }
        return temp;
    }
}
