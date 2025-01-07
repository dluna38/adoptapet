package co.lunadev.adoptaweb.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES) // Expira 10 minutos después de escribir
                .maximumSize(100); // Tamaño máximo de 100 elementos
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        /*cacheManager.registerCustomCache("shortLivedCache",
                Caffeine.newBuilder()
                        .expireAfterWrite(1, TimeUnit.MINUTES)
                        .maximumSize(100)
                        .build());*/

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.registerCustomCache("cacheForLong",Caffeine.newBuilder()
                .expireAfterWrite(12, TimeUnit.HOURS)
                .maximumSize(100)
                .build());
        cacheManager.setCaffeine(caffeine);
        return cacheManager;
    }
}
