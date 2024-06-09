package com.ticketing.solution.adapter.pay;

import com.ticketing.solution.application.port.in.PaymentFacade;
import com.ticketing.solution.domain.payment.ProcessPrePaymentCommand;
import com.ticketing.solution.adapter.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    private final PaymentFacade paymentFacade;
    private final PaymentWebMapper paymentWebMapper;

    @PostMapping("/payments/pre-process")
    public ResponseEntity<Void> prePaymentProcess(@RequestBody PaymentPreRequest paymentRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ProcessPrePaymentCommand command = paymentWebMapper.mapToProcessPrePaymentCommand(paymentRequest);
        paymentFacade.prePaymentProcess(command, userDetails.getMember());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/payments/post-process")
    public ResponseEntity<Void> postPaymentProcess(@RequestBody PaymentPostRequest paymentRequest) {
        paymentFacade.postPaymentProcess(paymentRequest.imp_uid());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentWebMapper.mapToPaymentResponse(paymentFacade.getPayment(paymentId)));
    }
}
