package rs.symbolic.redis.geo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.symbolic.redis.geo.dao.GeolocationDAO;
import rs.symbolic.redis.geo.model.Location;
import rs.symbolic.redis.geo.model.LocationDistance;

import javax.annotation.Resource;
import java.util.List;

/**
 * Default implementation of {@link GeolocationService}.
 */
@Service
public class DefaultGeolocationService implements GeolocationService {

    /**
     * The geolocation DAO.
     */
    @Resource
    GeolocationDAO geolocationDAO;

    @Override
    public void add(Location location) {
        geolocationDAO.add(location);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<LocationDistance> getNearbyLocations(Location location, double distance, String distanceUnit) {
        return geolocationDAO.getNearbyLocations(location, distance, distanceUnit);
    }
}
