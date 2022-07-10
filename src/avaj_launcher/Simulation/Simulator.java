package avaj_launcher.Simulation;

import avaj_launcher.Flyable.*;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {

    static File toWrite;
    private static int num;

    public static void main (String[] args) {
        if (args.length < 1) {
            System.out.println("Invalid number of arguments.");
            System.exit(-1);
        }
        ArrayList<String> aircraftsToString = new ArrayList<>();
        receiveInfoFromInput(args, aircraftsToString);
        createOutput();
        WeatherTower weatherTower = new WeatherTower();
        launchAircrafts(weatherTower, aircraftsToString);
        runSimulation(weatherTower);
    }

    private static void receiveInfoFromInput(String[] args, ArrayList<String> aircraftsToString) {
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
            System.exit(-1);
        } catch (FileNotFoundException e) {
            System.out.println("File error. " + e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Error. " + e.getMessage());
            System.exit(-1);
        }
    }

    private static void createOutput() {
        toWrite = new File("simulation.txt");
        try { FileWriter writer = new FileWriter(toWrite);
            writer.write("");
        } catch (IOException e) {
            System.out.println("Error with output file. " + e.getMessage());
            System.exit(-1);
        }
    }

    private static void launchAircrafts(WeatherTower weatherTower, ArrayList<String> aircraftsToString) {
        AircraftLauncher aircraftLauncher = new AircraftLauncher();
        ArrayList<Flyable> aircraftArray = new ArrayList<>();
        for (String aircraft : aircraftsToString) {
            Flyable air = aircraftLauncher.createAircraft(aircraft, weatherTower);
            if (air != null)
                aircraftArray.add(air);
        }
    }

    private static void runSimulation(WeatherTower weatherTower) {
        for(int i = 0; i < num; i++) {
            addToFile("\n========= " + (i + 1) + " SIMULATION CIRCLE =========");
            weatherTower.changeWeather();
        }
    }

    public static void addToFile(String str) {
        try (FileWriter writer = new FileWriter(toWrite, true)) {
            writer.write(str + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to file. " + e.getMessage());
            System.exit(-1);
        }
    }
}
