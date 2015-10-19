package rs.symbolic.redis.geo.model;

import java.math.BigDecimal;

/**
 * Geo location represented by name, longitude and latitude.
 */
public class Location {

    private final BigDecimal longitude;
    private final BigDecimal latitude;
    private final String name;

    /**
     * Instantiates a new {@code Location}.
     *
     * @param longitude the longitude
     * @param latitude  the latitude
     * @param name      the name of the location
     */
    public Location(BigDecimal longitude, BigDecimal latitude, String name) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the longitude.
     *
     * @return the longitude
     */
    public BigDecimal getLongitude() {
        return longitude;
    }

    /**
     * Gets the latitude.
     *
     * @return the latitude
     */
    public BigDecimal getLatitude() {
        return latitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", name='" + name + '\'' +
                '}';
    }
}
