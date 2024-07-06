package com.ticketing.solution.adapter.payment.portone;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.ticketing.solution.application.port.out.payment.ThirdPartyPaymentPort;
import com.ticketing.solution.application.service.exception.PaymentFailedException;
import com.ticketing.solution.domain.payment.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class PortOneAdapter implements ThirdPartyPaymentPort {

    private final IamportClient iamportClient;

    private final PortOnePaymentMapper portOnePaymentMapper;

    @CircuitBreaker(name = "portOneGetPayment", fallbackMethod = "paymentFailedFallback")
    public Payment getPaymentInfo(String impUid){
        try {
            return portOnePaymentMapper.mapToPayment(iamportClient.paymentByImpUid(impUid).getResponse());
        } catch (IamportResponseException | IOException e) {
            throw new PaymentFailedException(e.getMessage());
        }
    }

    @CircuitBreaker(name = "portOneCancelPayment", fallbackMethod = "paymentFailedFallback")
    public void cancelPayment(String impUid) {
        try {
            CancelData cancelData = new CancelData(impUid, true);
            iamportClient.cancelPaymentByImpUid(cancelData);
        } catch (IamportResponseException | IOException e) {
            throw new PaymentFailedException(e.getMessage());
        }
    }

    private Payment paymentFailedFallback(String impUid, Throwable throwable) {
        log.error("Payment failed for impUid: {}", impUid, throwable);
        throw new PaymentFailedException(throwable.getMessage());
    }

}
