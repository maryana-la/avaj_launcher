package avaj_launcher.Flyable;

import avaj_launcher.Simulation.InvalidCoordinatesException;

public abstract class AircraftFactory {
    public Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
            throws InvalidCoordinatesException, NullPointerException {
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
