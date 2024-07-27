package com.example.dblock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long maxUser;

    private Long nowUser;

    public Reservation(Long maxUser) {
        this.maxUser = maxUser;
        this.nowUser = 0L;
    }

    public void increaseUser() {
        if (nowUser + 1 > maxUser) {
            throw new IllegalStateException();
        }
        nowUser = nowUser + 1;
    }
}
