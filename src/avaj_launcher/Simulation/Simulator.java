package avaj_launcher.Simulation;

import avaj_launcher.Weather.WeatherProvider;
import avaj_launcher.Flyable.*;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class Simulator {

    static File toWrite;

    public static void main (String[] args) {
        ArrayList<String> aircraftsToString = new ArrayList<>();
        if (args.length < 1) {
            System.out.println("Invalid number of arguments.");
            exit(-1);
        }
        int num = receiveInfoFromInput(args, aircraftsToString);

//        creating aircrafts
        createOutput();
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
           addToFile((i + 1) + " ======================================");
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
            System.out.println("Error. Other " + e.getMessage());
            exit(-1);
        }
        return num;
    }


    private static void createOutput() {
        toWrite = new File("simulation.txt");
        try (FileWriter writer = new FileWriter(toWrite)) {
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error with output file. " + e.getMessage());
            exit(-1);
        }
    }

    public static void addToFile(String str) {
        try (FileWriter writer = new FileWriter(toWrite, true)) {
            writer.write(str + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file file. " + e.getMessage());
            exit(-1);
        }

    }
}
