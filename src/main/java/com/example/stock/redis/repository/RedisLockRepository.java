package com.example.stock.redis.repository;


import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisLockRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Boolean lock(Long key) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key), "lock", Duration.ofMillis(3_000));
    }

    public boolean unlock(Long key) {
        return Boolean.TRUE.equals(redisTemplate
                .delete(generateKey(key)));
    }

    private String generateKey(Long key) {
        return key.toString();
    }
}
