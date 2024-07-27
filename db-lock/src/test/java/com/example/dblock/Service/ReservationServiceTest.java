package com.example.dblock.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.dblock.domain.Reservation;
import com.example.dblock.domain.ReservationRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings("NonAsciiCharacters")
@SpringBootTest
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    void init() {

    }

    @Test
    void 기본테스트_1() {
        //given
        var reservation = new Reservation(50L);
        var savedReservation = reservationRepository.save(reservation);

        //when
        reservationService.reserve(savedReservation.getId());

        //then
        Long nowUser = reservationRepository.findById(savedReservation.getId())
                                            .orElseThrow(NoSuchElementException::new)
                                            .getNowUser();

        assertEquals(nowUser, 1);
    }

    @Test
    void 기본테스트_100() {
        //given
        var reservation = new Reservation(50L);
        var savedReservation = reservationRepository.save(reservation);

        //when
        for (int i = 0; i < 50; i++) {
            reservationService.reserve(reservation.getId());
        }

        //then
        Long nowUser = reservationRepository.findById(savedReservation.getId())
                                            .orElseThrow(NoSuchElementException::new)
                                            .getNowUser();

        assertEquals(nowUser, 50);
    }
}

