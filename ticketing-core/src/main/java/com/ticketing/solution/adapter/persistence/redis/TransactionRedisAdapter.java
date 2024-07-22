package com.ticketing.solution.adapter.persistence.redis;

import com.ticketing.solution.application.port.out.persistence.transaction.TransactionPort;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class TransactionRedisAdapter implements TransactionPort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public <T> T invoke(Supplier<T> operation) {
        return executeRedisTransaction(operation);
    }

    @Override
    public void invokeWithoutResult(Supplier<Object> operation) {
        executeRedisTransaction(() -> {
            operation.get();
            return null;
        });
    }

    private <T> T executeRedisTransaction(Supplier<T> operation) {
        return redisTemplate.execute(new SessionCallback<T>() {
            @Override
            public <K, V> T execute(@NotNull RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                try {
                    T result = operation.get();
                    operations.exec();
                    return result;
                } catch (Exception ex) {
                    operations.discard();
                    throw ex;
                }
            }
        });
    }
}