package com.ticketing.solution.infrastructure.portOne;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.Payment;
import com.ticketing.solution.infrastructure.exception.PaymentFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class PortOneClient {

    private final IamportClient iamportClient;

    public Payment getPaymentInfo(String impUid){
        try {
            return iamportClient.paymentByImpUid(impUid).getResponse();
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
