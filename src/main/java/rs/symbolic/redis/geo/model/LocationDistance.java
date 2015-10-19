package rs.symbolic.redis.geo.model;

import java.io.Serializable;

/**
 * Result object containing location name and distance value.
 */
public class LocationDistance implements Serializable {
    private final String name;
    private final double distance;

    /**
     * Instantiates a new {@code LocationDistance}.
     *
     * @param name     the name
     * @param distance the distance
     */
    public LocationDistance(String name, double distance) {
        this.name = name;
        this.distance = distance;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets distance.
     *
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }
}
