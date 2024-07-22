package com.ticketing.solution.application.port.out.persistence.transaction;

import java.util.function.Supplier;

public interface TransactionPort {

    <T> T invoke(Supplier<T> operation);

    void invokeWithoutResult(Supplier<Object> operation);
}
