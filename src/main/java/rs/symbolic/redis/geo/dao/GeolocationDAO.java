package rs.symbolic.redis.geo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Repository;
import rs.symbolic.redis.geo.model.Location;
import rs.symbolic.redis.geo.model.LocationDistance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class GeolocationDAO {

    @Autowired
    RedisScript<String> geoadd;

    @Autowired
    RedisScript<List> georadius;

    @Autowired
    RedisTemplate<String, String> redisTemplate;


    public void add(Location location) {
        redisTemplate.execute(geoadd, Arrays.asList("POI", location.getName()), location.getLongitude().toString(), location.getLatitude().toString());
    }

    @SuppressWarnings("unchecked")
    public List<LocationDistance> getNearbyLocations(Location location, double distance, String unit) {
        final List<LocationDistance> locations = new ArrayList<>();
        final List<List<String>> poi = redisTemplate.execute(georadius, Collections.singletonList("POI"), location.getLongitude().toString(), location.getLatitude().toString(), String.valueOf(distance), unit, "withdist");
        locations.addAll(poi.stream().map(p -> new LocationDistance(p.get(0), Double.valueOf(p.get(1)))).collect(Collectors.toList()));

        return locations;
    }
}
