package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.application.port.out.persistence.transaction.TransactionPort;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

@RequiredArgsConstructor
public class TransactionJpaAdapter implements TransactionPort {

    private final TransactionTemplate transactionTemplate;

    public <T> T invoke(Supplier<T> operation) {
        return transactionTemplate.execute(transactionStatus -> {
            return executeOperation(operation, transactionStatus);
        });
    }

    @Override
    public void invokeWithoutResult(Supplier<Object> operation) {
        transactionTemplate.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(@NotNull TransactionStatus status) {
                executeOperation(operation, status);
            }
        });
    }

    private <T> T executeOperation(Supplier<T> operation, TransactionStatus status) {
        try {
            return operation.get();
        } catch (Exception ex) {
            status.setRollbackOnly();
            throw ex;
        }
    }
}