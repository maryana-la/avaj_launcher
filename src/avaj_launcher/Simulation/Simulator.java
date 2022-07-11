package avaj_launcher.Simulation;

import avaj_launcher.Flyable.AircraftLauncher;
import avaj_launcher.Flyable.Flyable;

import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {
    static File toWrite;
    private static int num;
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";

    public static void main (String[] args) {
        if (args.length < 1) {
            printError("Invalid number of arguments.", -1);
        } else if (args.length == 2 && "MD5".equals(args[1])) {
            MD5.encryptFile(args[0]);
        } else {
            ArrayList<String> aircraftsToString = new ArrayList<>();
            receiveInfoFromInput(args[0], aircraftsToString);
            createOutput();
            WeatherTower weatherTower = new WeatherTower();
            launchAircrafts(weatherTower, aircraftsToString);
            runSimulation(weatherTower);
        }
//        while (true) {}
    }

    private static void receiveInfoFromInput(String argName, ArrayList<String> aircraftsToString) {
        try {
            File file = new File(argName);

            if (argName.endsWith(".MD")) {
                num = MD5.decryptFile(file, aircraftsToString);
                if (num <= 0)
                    throw new NumberFormatException("Not a positive integer.");
            } else {
                Scanner sc = new Scanner(file);
                num = Integer.parseInt(sc.nextLine());
                if (num <= 0)
                    throw new NumberFormatException("Not a positive integer.");
                while (sc.hasNextLine()) {
                    aircraftsToString.add(sc.nextLine());
                }
            }
        } catch (NumberFormatException e) {
            printError("Error. Invalid number of simulations. " + e.getMessage(), -1);
        } catch (FileNotFoundException e) {
            printError("File error. " + e.getMessage(), -1);
        } catch (Exception e) {
            printError("Error. " + e.getMessage(), -1);
        }
    }

    private static void createOutput() {
        toWrite = new File("simulation.txt");
        try { FileWriter writer = new FileWriter(toWrite);
            writer.write("");
        } catch (IOException e) {
            printError("Error with output file. " + e.getMessage(), -1);
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
            printError("Error writing to file. " + e.getMessage(), -1);
        }
    }

    public static void printError(String msg, int err) {
        if (err < 0) {
            System.err.println(msg);
//            System.err.println(msg);
            System.exit(err);
        } else {
            System.out.println(YELLOW + msg + RESET);
        }
    }
}
