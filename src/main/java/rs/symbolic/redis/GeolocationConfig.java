package rs.symbolic.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.List;

/**
 * The type Geolocation config.
 */
@Configuration
public class GeolocationConfig {

    /**
     * Georadius redis script.
     *
     * @return the redis script
     */
    @Bean
    RedisScript<List> georadius() {
        final DefaultRedisScript<List> script = new DefaultRedisScript<>();
        script.setResultType(List.class);
        script.setLocation(new ClassPathResource("/META-INF/scripts/georadius-withdist.lua"));

        return script;
    }

    /**
     * Geoadd redis script.
     *
     * @return the redis script
     */
    @Bean
    RedisScript<String> geoadd() {
        final DefaultRedisScript<String> script = new DefaultRedisScript<>();
        script.setLocation(new ClassPathResource("/META-INF/scripts/geoadd.lua"));
        script.setResultType(String.class);

        return script;
    }
}
