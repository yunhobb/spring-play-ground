package com.example.dblock.Service;


import com.example.dblock.domain.Reservation;
import com.example.dblock.domain.ReservationRepository;
import java.util.NoSuchElementException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Transactional
    public synchronized void reserve(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                                                       .orElseThrow(NoSuchElementException::new);

        reservation.increaseUser();
    }
}
