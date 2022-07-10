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
    private static long idCounter;

    protected Aircraft(String name, Coordinates coordinates) {
        this.name = name;
        this.coordinates = coordinates;
        id = nextId();
    }

    private long nextId() { return ++idCounter; }

    public long getId() { return id; }
    public String getName() { return name; }

    String getOutput() {
        return getClass().getSimpleName() + "#" + name + "(" + id + ")";
    }
}

class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weatherInfo = weatherTower.getWeather(coordinates);
        switch (weatherInfo) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                break;
            case "RAIN":
                coordinates.setLongitude(coordinates.getLongitude() + 5);
                break;
            case "FOG":
                coordinates.setLongitude(coordinates.getLongitude() + 1);
                break;
            case "SNOW":
            default:
                coordinates.setHeight(coordinates.getHeight() - 12);
                break;
        }
        System.out.println(getOutput() + ": " + weatherInfo);

        if(coordinates.getHeight() <= 0) {
            System.out.println(getOutput() + " landing");
            weatherTower.unregister(this);
            System.out.println("Tower says: " + getOutput() + " unregistered from weather tower.");
            System.out.println("latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude() + " height: " + coordinates.getHeight());
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        if (this.coordinates.getHeight() > 0) {
            this.weatherTower.register(this);
            System.out.println("Tower says: " + getOutput() + " registered to weather tower.");
        }
    }
}

class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weatherInfo = weatherTower.getWeather(coordinates);
        switch (weatherInfo) {
            case "SUN":
                coordinates.setLatitude(coordinates.getLatitude() + 10);
                coordinates.setHeight(coordinates.getHeight() + 2);
                break;
            case "RAIN":
                coordinates.setLatitude(coordinates.getLatitude() + 5);
                break;
            case "FOG":
                coordinates.setLatitude(coordinates.getLatitude() + 1);
                break;
            case "SNOW":
            default:
                coordinates.setHeight(coordinates.getHeight() - 7);
                break;
        }
        System.out.println(getOutput() + ": " + weatherInfo);

        if(coordinates.getHeight() <= 0) {
            System.out.println(getOutput() + " landing");
            weatherTower.unregister(this);
            System.out.println("Tower says: " + getOutput() + " unregistered from weather tower.");
            System.out.println("latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude() + " height: " + coordinates.getHeight());
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        if (this.coordinates.getHeight() > 0) {
            this.weatherTower.register(this);
            System.out.println("Tower says: " + getOutput() + " registered to weather tower.");
        }
    }
}

class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    @Override
    public void updateCondition() {
        String weatherInfo = weatherTower.getWeather(coordinates);
        switch (weatherInfo) {
            case "SUN":
                coordinates.setLongitude(coordinates.getLongitude() + 2);
                coordinates.setHeight(coordinates.getHeight() + 4);
                break;
            case "RAIN":
                coordinates.setHeight(coordinates.getHeight() - 5);
                break;
            case "FOG":
                coordinates.setHeight(coordinates.getHeight() - 3);
                break;
            case "SNOW":
            default:
                coordinates.setHeight(coordinates.getHeight() - 15);
                break;
        }
        System.out.println(getOutput() + ": " + weatherInfo);

        if(coordinates.getHeight() <= 0) {
            System.out.println(getOutput() + " landing");
            weatherTower.unregister(this);
            System.out.println("Tower says: " + getOutput() + " unregistered from weather tower.");
            System.out.println("latitude: " + coordinates.getLatitude() + " longitude: " + coordinates.getLongitude() + " height: " + coordinates.getHeight());
        }
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        if (this.coordinates.getHeight() > 0) {
            this.weatherTower.register(this);
            System.out.println("Tower says: " + getOutput() + " registered to weather tower.");
        }
    }
}

/******************************************/

abstract class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws InvalidCoordinatesException, NullPointerException {
        if (longitude <= 0)
            throw new InvalidCoordinatesException("Longitude should be a positive number.");
        if (latitude <= 0)
            throw new InvalidCoordinatesException("Latitude should be a positive number.");
        if (height < 0 || height > 100)
            throw new InvalidCoordinatesException("Height should be in a range 0 - 100.");
        Coordinates coord = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "BALOON":
                return new Baloon(name, coord);
            case "HELICOPTER":
                return new Helicopter(name, coord);
            case "JETPLANE":
                return new JetPlane(name, coord);
            default:
                throw new NullPointerException("Invalid aircraft type.");
        }
    }
}

class AircraftLauncher extends AircraftFactory {
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
            System.out.println(e.getMessage());
        }
        return temp;
    }
}
