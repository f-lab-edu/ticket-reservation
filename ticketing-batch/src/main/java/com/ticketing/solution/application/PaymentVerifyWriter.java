package com.ticketing.solution.application;

import com.ticketing.solution.application.port.in.PaymentOperationPort;
import com.ticketing.solution.application.port.out.payment.ThirdPartyPaymentPort;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class PaymentVerifyWriter implements ItemWriter<Payment> {

    private final JdbcTemplate jdbcTemplate;
    private final ThirdPartyPaymentPort thirdPartyPaymentPort;
    private final PaymentOperationPort paymentOperationPort;
    private final List<Long> successPaymentIds;

    @Override
    public void write(Chunk<? extends Payment> chunk) {
        chunk.getItems().forEach(this::processPayment);

        if (!successPaymentIds.isEmpty()) {
            updatePaymentStatus();
        }
    }

    private void processPayment(Payment payment) {
        Payment thirdPartyInfo = thirdPartyPaymentPort.getPaymentInfo(payment.getImpUid());
        if (payment.getAmount().equals(thirdPartyInfo.getAmount())) {
            successPaymentIds.add(payment.getId());
        } else {
            paymentOperationPort.cancelPayment(thirdPartyInfo.getMerchantUid(), payment.getId());
        }
    }

    private void updatePaymentStatus() {
        String ids = successPaymentIds.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
        jdbcTemplate.update(String.format(
                "UPDATE reservation r " +
                        "JOIN payment p ON r.payment_id = p.id " +
                        "SET r.status = '%d', p.approved = %b " +
                        "WHERE p.id IN (%s)", ReservationStatus.RESERVED.ordinal(), true, ids));
    }
}