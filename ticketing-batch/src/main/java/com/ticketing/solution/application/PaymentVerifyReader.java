package com.ticketing.solution.application;

import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentVerifyReader {
    @Value("${chunk.size}")
    private int chunkSize;

    private final DataSource dataSource;

    @Bean
    public JdbcCursorItemReader<Payment> read() {
        return new JdbcCursorItemReaderBuilder<Payment>()
                .name("jdbcCursorItemReader")
                .fetchSize(chunkSize)
                .dataSource(dataSource)
                .rowMapper(new DataClassRowMapper<Payment>(Payment.class))
                .sql("SELECT p.id, p.merchantUid, p.amount " +
                        "FROM payment p " +
                        "JOIN reservation r ON r.payment_id = p.id " +
                        "WHERE r.status = 2")
                .build();
    }
}