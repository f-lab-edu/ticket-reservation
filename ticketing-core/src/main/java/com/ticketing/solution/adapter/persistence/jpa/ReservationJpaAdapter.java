package com.ticketing.solution.adapter.persistence.jpa;

import com.ticketing.solution.adapter.persistence.jpa.repository.ReservationJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.MemberJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.mapper.PaymentJpaMapper;
import com.ticketing.solution.adapter.persistence.jpa.mapper.ReservationJpaMapper;
import com.ticketing.solution.application.port.out.persistence.ReservationPersistencePort;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.payment.Payment;
import com.ticketing.solution.domain.reservation.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReservationJpaAdapter implements ReservationPersistencePort {

    private static final int MAX_PAGE_SIZE = 16;

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationJpaMapper reservationJpaMapper;

    private final MemberJpaMapper memberJpaMapper;

    private final PaymentJpaMapper paymentJpaMapper;

    @Override
    public Reservation save(Reservation reservation) {
        var reservationEntity = reservationJpaMapper.mapToEntity(reservation);
        return reservationJpaMapper.mapToDomain(reservationJpaRepository.save(reservationEntity));
    }

    @Override
    public Reservation findByIdAndMember(Long reservationId, Member member) {
        var reservation = reservationJpaRepository.findByIdAndMember(reservationId, memberJpaMapper.mapToEntity(member)).orElseThrow();
        return reservationJpaMapper.mapToDomain(reservation);
    }

    @Override
    public Reservation findById(Long reservationId) {
        return reservationJpaMapper.mapToDomain(reservationJpaRepository.findById(reservationId).orElseThrow());
    }

    @Override
    public List<Reservation> findByMember(int page, Member member) {
        PageRequest pageRequest = PageRequest.of(page - 1, MAX_PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id"));
        var memberEntity = memberJpaMapper.mapToEntity(member);
        return reservationJpaMapper.mapToDomains(reservationJpaRepository.findByMember(pageRequest, memberEntity));
    }

    @Override
    public Reservation findByPayment(Payment payment) {
        var paymentEntity = paymentJpaMapper.mapToEntity(payment);
        return reservationJpaMapper.mapToDomain(reservationJpaRepository.findByPayment(paymentEntity).orElseThrow());
    }
}
