package com.ticketing.solution.config;

import com.ticketing.solution.application.PaymentVerifyReader;
import com.ticketing.solution.application.PaymentVerifyWriter;
import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class PaymentJobConfig {
    @Value("${chunk.size}")
    private int chunkSize;

    private final PaymentVerifyWriter paymentVerifyWriter;
    private final PaymentVerifyReader paymentVerifyReader;


    @Bean
    public Job paymentJob(JobRepository jobRepository, Step paymentStep) {
        return new JobBuilder("paymentJob", jobRepository)
                .preventRestart()
                .incrementer(new RunIdIncrementer())
                .start(paymentStep)
                .build();
    }

    @Bean
    public Step paymentStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("paymentStep", jobRepository)
                .<Payment, Payment>chunk(chunkSize, transactionManager)
                .reader(jdbcCursorItemReader())
                .writer(jdbcCursorItemWriter())
                .faultTolerant()
                .skipPolicy((Throwable t, long skipCount) -> { return true;})
                .build();
    }

    public JdbcCursorItemReader<Payment> jdbcCursorItemReader() {
        return paymentVerifyReader.read();
    }
    public ItemWriter<Payment> jdbcCursorItemWriter() {
        return new ItemWriter<Payment>() {
            @Override
            public void write(Chunk<? extends Payment> chunk) {
                paymentVerifyWriter.write(chunk);
            }
        };
    }
}