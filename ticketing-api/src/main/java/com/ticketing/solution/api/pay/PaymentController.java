package com.ticketing.solution.api.pay;

import com.ticketing.solution.application.PaymentFacade;
import com.ticketing.solution.infrastructure.config.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    private final PaymentFacade paymentFacade;
    private final PaymentMapper paymentMapper;

    @PostMapping("/payments/pre-process")
    public ResponseEntity<Void> prePaymentProcess(@RequestBody PaymentPreRequest paymentRequest, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        paymentFacade.prePaymentProcess(paymentRequest.merchantUid(), paymentRequest.showId(), paymentRequest.amount(), userDetails);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/payments/post-process")
    public ResponseEntity<Void> postPaymentProcess(@RequestBody PaymentPostRequest paymentRequest) {
        paymentFacade.postPaymentProcess(paymentRequest.imp_uid());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/payments/{paymentId}")
    public ResponseEntity<PaymentResponse> getPayment(@PathVariable Long paymentId) {
        return ResponseEntity.ok(paymentMapper.mapToPaymentResponse(paymentFacade.getPayment(paymentId)));
    }
}
