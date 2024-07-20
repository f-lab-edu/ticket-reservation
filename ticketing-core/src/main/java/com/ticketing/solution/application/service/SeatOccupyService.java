package com.ticketing.solution.application.service;

import com.ticketing.solution.application.port.in.SeatOccupyOperationPort;
import com.ticketing.solution.application.port.out.persistence.SeatOccupyPersistencePort;
import com.ticketing.solution.application.service.exception.AccessDeniedException;
import com.ticketing.solution.domain.member.Member;
import com.ticketing.solution.domain.seat.Seat;
import com.ticketing.solution.domain.seat.SeatOccupy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SeatOccupyService implements SeatOccupyOperationPort {

    private final SeatOccupyPersistencePort seatOccupyPersistencePort;

    @Override
    @Transactional
    public void addSeatOccupy(SeatOccupy seatOccupy) {
        seatOccupyPersistencePort.addSeatOccupy(seatOccupy);
    }

    @Override
    @Transactional
    public SeatOccupy findBySeat(Seat seat) {
        return seatOccupyPersistencePort.findBySeat(seat);
    }

    @Override
    @Transactional
    public void setReserved(boolean isReserved, SeatOccupy seatOccupy) {
        seatOccupyPersistencePort.setReserved(isReserved, seatOccupy);
    }

    @Override
    public void verifyPermission(Long seatId, Member member) {
        SeatOccupy seatOccupy = seatOccupyPersistencePort.getSeatOccupyBySeatId(seatId);
        if (!seatOccupy.getMember().getId().equals(member.getId())) {
            throw new AccessDeniedException();
        }
    }
}
