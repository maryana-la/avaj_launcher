package avaj_launcher.Simulation;

import avaj_launcher.Flyable.AircraftLauncher;
import avaj_launcher.Flyable.Flyable;
import avaj_launcher.Utils.LogFile;
import avaj_launcher.Utils.MD5;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Simulator {
    private static int num;
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";

    public static void main (String[] args) {
        if (args.length == 2 && "MD5".equals(args[1])) {
            MD5.encryptFile(args[0]);
        } else if (args.length != 1) {
            printError("Invalid number of arguments.", -1);
        } else {
            ArrayList<String> aircraftsToString = new ArrayList<>();
            WeatherTower weatherTower = new WeatherTower();

            receiveInfoFromInput(args[0], aircraftsToString);
            LogFile.createOutput();
            launchAircrafts(weatherTower, aircraftsToString);
            runSimulation(weatherTower);
            LogFile.closeWriter();
        }
    }

    private static void receiveInfoFromInput(String argName, ArrayList<String> aircraftsToString) {
        try {
            File file = new File(argName);

            if (argName.endsWith(".MD5")) {
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
            LogFile.addToFile("\n========= " + (i + 1) + " SIMULATION CIRCLE =========");
            weatherTower.changeWeather();
        }
    }

    public static void printError(String msg, int err) {
        if (err < 0) {
            System.err.println(msg);
            System.exit(err);
        } else {
            System.out.println(YELLOW + msg + RESET);
        }
    }
}
