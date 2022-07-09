package avaj_launcher;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
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
//        System.out.println(aircraftArray.toString());




    }
}

/*****************************************/

//todo maybe add methods for changing weather
//class Simulation {
//    private int longitude;
//    private int latitude;
//    private int height;
//
//}
//
//

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

//    public void setLongitude(int longitude) { this.longitude = longitude; }
//
//    public void setLatitude(int latitude) { this.latitude = latitude; }
//
//    public void setHeight(int height) { this.height = height; }
}

/******************************************/

abstract class Tower {
    private ArrayList<Flyable> observers;

    Tower() {
        observers = new ArrayList<>();
    }

    public void register(Flyable flyable) {
        this.observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
    }

    protected void conditionsChanged() {}
}

class WeatherTower extends Tower {
    public WeatherTower() {
        super();
    }

    public String getWeather(Coordinates coordinates) {
        return null;
    }

    void changeWeather() {}
}

/******************************************/


class WeatherProvider {
    private static final WeatherProvider weatherProvider = new WeatherProvider();
    private static final String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        int num = new Random().nextInt(coordinates.getLatitude() * 4 + coordinates.getLongitude() * 3 + coordinates.getHeight() * 2) % 4;
        return weather[num];
    }
}

