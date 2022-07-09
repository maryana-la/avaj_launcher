package avaj_launcher;


import java.util.Locale;

interface Flyable {
    public void updateCondition();
    public void registerTower(WeatherTower tower);
}

abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 1;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        id = nextId();
//        System.out.println(this.name + " " + this.id);
    }

    private long nextId() {
        return idCounter++;
    }
//
//    String getName() { return this.name; }
//
//    long getId() { return id; }

    String getOutput() {
        return "#" + name + "(" + id + ")";
    }
}

class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {}

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: " + this.getClass().getSimpleName() + getOutput() + " registered to weather tower.");
    }
}

class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {}

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: " + this.getClass().getSimpleName() + getOutput() + " registered to weather tower.");
    }
}

class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {}

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
        System.out.println("Tower says: " + this.getClass().getSimpleName() + getOutput() + " registered to weather tower.");
    }
}

/******************************************/

abstract class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        if (longitude < 0)
            longitude = 0;
        if (latitude < 0)
            latitude = 0;
        if (height < 0 || height > 100)
            height = height < 0 ? 0 : (height < 100 ? height : 100);
        Coordinates coord = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "BALOON":
                return new Baloon(name, coord);
            case "HELICOPTER":
                return new Helicopter(name, coord);
            case "JETPLANE":
                return new JetPlane(name, coord);
            default:
                return null;
        }
    }
}

class AircraftLauncher extends AircraftFactory {
    public Flyable createAircraft(String str) {
        String[] temp = str.split(" ");
        return newAircraft(temp[0].toUpperCase(Locale.ROOT), temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), Integer.parseInt(temp[4]));
    }
}

//class AircraftStore extends AircraftFactory {
//
//}
