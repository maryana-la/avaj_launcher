package avaj_launcher;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Simulator {
    public static void main (String[] args) {
        ArrayList<String> aircraftsToString = new ArrayList<>();
        int num = receiveInfoFromInput(args, aircraftsToString);

//        creating aircrafts
        WeatherTower weatherTower = new WeatherTower();
        AircraftLauncher aircraftLauncher = new AircraftLauncher();
        ArrayList<Flyable> aircraftArray = new ArrayList<>();
        for (String aircraft : aircraftsToString) {
            Flyable air = aircraftLauncher.createAircraft(aircraft, weatherTower);
            if (air != null)
                aircraftArray.add(air);
        }

//        weather simulation
        for(int i = 0; i < num; i++) {
            System.out.println((i + 1) + " ======================================");
            weatherTower.changeWeather();
        }
    }

    private static int receiveInfoFromInput(String[] args, ArrayList<String> aircraftsToString) {
        int num = 0;
        try {
            File file = new File(args[0]);
            Scanner sc = new Scanner(file);

            num = Integer.parseInt(sc.nextLine());
            if (num <= 0)
                throw new NumberFormatException("Not a positive integer.");
            while(sc.hasNextLine()) {
                aircraftsToString.add(sc.nextLine());
            }
        } catch (NumberFormatException e) {
            System.out.println("Error. Number of simulation is invalid. " + e.getMessage());
            exit(-1);
        } catch (FileNotFoundException e) {
            System.out.println("File error. " + e.getMessage());
            exit(-1);
        } catch (Exception e) {
            System.out.println("Error. " + e.getMessage());
            exit(-1);
        }
        return num;
    }
}
