package rs.symbolic.redis;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rs.symbolic.redis.geo.model.Location;
import rs.symbolic.redis.geo.model.LocationDistance;
import rs.symbolic.redis.geo.service.GeolocationService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link GeolocationService}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringDataRedisSampleApplication.class)
public class GeolocationServiceTest {

    /**
     * The Redis template.
     */
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * The Geolocation Service.
     */
    @Autowired
    GeolocationService service;

    /**
     * Sets up the test.
     */
    @Before
    public void setUp() {
        service.add(new Location(BigDecimal.valueOf(44.802312), BigDecimal.valueOf(20.460585), "Elementary School"));
        service.add(new Location(BigDecimal.valueOf(44.802451), BigDecimal.valueOf(20.458968), "High school"));
        service.add(new Location(BigDecimal.valueOf(44.801970), BigDecimal.valueOf(20.459198), "Hospital"));
        service.add(new Location(BigDecimal.valueOf(44.802244), BigDecimal.valueOf(20.457497), "Pharmacy-1"));
        service.add(new Location(BigDecimal.valueOf(44.802296), BigDecimal.valueOf(20.45896), "Pharmacy-2"));
        service.add(new Location(BigDecimal.valueOf(44.802312), BigDecimal.valueOf(20.457757), "Supermarket"));
        service.add(new Location(BigDecimal.valueOf(44.802358), BigDecimal.valueOf(20.459906), "Hairdressing salon"));
    }

    /**
     * Gets nearby 300 m.
     */
    @Test
    public void getNearby300m() {
        final double distance = 300;
        final List<String> expectedPOI = Arrays.asList(
                "Supermarket",
                "Pharmacy-2",
                "High school",
                "Pharmacy-1",
                "Hospital");

        final List<LocationDistance> poi = service.getNearbyLocations(new Location(BigDecimal.valueOf(44.803942), BigDecimal.valueOf(20.457574), "My Location"), distance, "m");
        assertEquals(expectedPOI.size(), poi.size());

        for (LocationDistance loc : poi) {
            assertTrue(loc.getDistance() <= distance);
            assertTrue(expectedPOI.contains(loc.getName()));
        }
    }

    /**
     * Gets nearby 500 m.
     */
    @Test
    public void getNearby500m() {
        final double distance = 500;
        final List<String> expectedPOI = Arrays.asList(
                "Supermarket",
                "Pharmacy-2",
                "High school",
                "Hairdressing salon",
                "Elementary School",
                "Pharmacy-1",
                "Hospital");

        final List<LocationDistance> poi = service.getNearbyLocations(new Location(BigDecimal.valueOf(44.803942), BigDecimal.valueOf(20.457574), "My Location"), distance, "m");
        assertEquals(expectedPOI.size(), poi.size());

        for (LocationDistance loc : poi) {
            assertTrue(loc.getDistance() <= distance);
            assertTrue(expectedPOI.contains(loc.getName()));
        }
    }

    /**
     * Gets nearby 50 m.
     */
    @Test
    public void getNearby50m() {
        final double distance = 50;
        final List<String> expectedPOI = Collections.emptyList();

        final List<LocationDistance> poi = service.getNearbyLocations(new Location(BigDecimal.valueOf(44.803942), BigDecimal.valueOf(20.457574), "My Location"), distance, "m");
        assertEquals(expectedPOI.size(), poi.size());
    }

    /**
     * Tears down the test by deleting data from Redis.
     */
    @After
    public void tearDown() {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

}
