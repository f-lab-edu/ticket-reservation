package com.ticketing.solution.adapter.persistence;

import com.ticketing.solution.adapter.persistence.jpa.ReservationJpaRepository;
import com.ticketing.solution.adapter.persistence.jpa.mapper.MemberMapper;
import com.ticketing.solution.adapter.persistence.jpa.mapper.PaymentMapper;
import com.ticketing.solution.adapter.persistence.jpa.mapper.ReservationMapper;
import com.ticketing.solution.application.port.out.ReservationRepository;
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
public class ReservationRepositoryImpl implements ReservationRepository {

    private static final int MAX_PAGE_SIZE = 16;

    private final ReservationJpaRepository reservationJpaRepository;
    private final ReservationMapper reservationMapper;

    private final MemberMapper memberMapper;

    private final PaymentMapper paymentMapper;

    @Override
    public Reservation save(Reservation reservation) {
        var reservationEntity = reservationMapper.mapToEntity(reservation);
        return reservationMapper.mapToDomain(reservationJpaRepository.save(reservationEntity));
    }

    @Override
    public Reservation findById(Long reservationId) {
        var reservation = reservationJpaRepository.findById(reservationId).orElseThrow();
        return reservationMapper.mapToDomain(reservation);
    }

    @Override
    public List<Reservation> findByMember(int page, Member member) {
        PageRequest pageRequest = PageRequest.of(page - 1, MAX_PAGE_SIZE, Sort.by(Sort.Direction.DESC, "id"));
        var memberEntity = memberMapper.mapToEntity(member);
        return reservationMapper.mapToDomains(reservationJpaRepository.findByMember(pageRequest, memberEntity));
    }

    @Override
    public void deleteById(Long reservationId) {
        reservationJpaRepository.deleteById(reservationId);
    }

    @Override
    public Reservation findByPayment(Payment payment) {
        var paymentEntity = paymentMapper.mapToEntity(payment);
        return reservationMapper.mapToDomain(reservationJpaRepository.findByPayment(paymentEntity).orElseThrow());
    }
}
