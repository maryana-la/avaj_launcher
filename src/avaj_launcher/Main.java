package avaj_launcher;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main (String[] args) {
        ArrayList<String> aircrafts = new ArrayList<>();

        WeatherTower weatherTower = new WeatherTower();
        int num;


        // receiving information
        //todo move to a separate method
        try {
            File file = new File(args[0]);
            Scanner sc = new Scanner(file);

            num = Integer.parseInt(sc.nextLine());
            while(sc.hasNextLine()) {
                aircrafts.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }


//        creating aircrafts
        ArrayList<Flyable> aircraftArray = new ArrayList<>();
        AircraftLauncher aircraftLauncher = new AircraftLauncher();
        int i = 0;
        for (String aircraft : aircrafts) {
            aircraftArray.add(aircraftLauncher.createAircraft(aircraft));
            aircraftArray.get(i).registerTower(weatherTower);
            i++;
        }


        for(int k = 0; k < num; k++) {
            weatherTower.changeWeather();
            System.out.println(k + "======================================");
        }



    }
}

/*****************************************/

//todo maybe add methods for changing weather

class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = height;
    }

    public int getLongitude() { return this.longitude; }

    public int getLatitude() { return this.latitude; }

    public int getHeight() { return this.height; }

    public void setLongitude(int longitude) { this.longitude = longitude; }

    public void setLatitude(int latitude) { this.latitude = latitude; }

    public void setHeight(int height) { this.height = height; }
}

/******************************************/



