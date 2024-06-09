package com.ticketing.solution.adapter.payment.portone;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.ticketing.solution.application.port.in.exception.PaymentFailedException;
import com.ticketing.solution.application.port.out.payment.ThirdPartyPaymentPort;
import com.ticketing.solution.domain.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PortOneAdapter implements ThirdPartyPaymentPort {

    private final IamportClient iamportClient;

    private final PortOnePaymentMapper portOnePaymentMapper;

    public Payment getPaymentInfo(String impUid){
        try {
            return portOnePaymentMapper.mapToPayment(iamportClient.paymentByImpUid(impUid).getResponse());
        } catch (IamportResponseException | IOException e) {
            throw new PaymentFailedException(e.getMessage());
        }
    }

    public void cancelPayment(String impUid) {
        try {
            CancelData cancelData = new CancelData(impUid, true);
            iamportClient.cancelPaymentByImpUid(cancelData);
        } catch (IamportResponseException | IOException e) {
            throw new PaymentFailedException(e.getMessage());
        }
    }

}
