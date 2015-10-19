package rs.symbolic.redis.geo.service;

import rs.symbolic.redis.geo.model.Location;
import rs.symbolic.redis.geo.model.LocationDistance;

import java.util.List;

/**
 * The interface Geolocation service.
 */
public interface GeolocationService {

    /**
     * Adds location to a database.
     *
     * @param location the location
     */
    void add(Location location);

    /**
     * Returns nearby locations from the given distance of start location.
     *
     * @param location the location from which distance is measured
     * @param distance the distance
     * @param distanceUnit     the distanceUnit
     * @return the nearby locations
     */
    List<LocationDistance> getNearbyLocations(Location location, double distance, String distanceUnit);
}
