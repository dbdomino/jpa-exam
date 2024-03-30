package com.minod.jpa.domain.embededtype;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;
@Embeddable
public class PeriodC {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public PeriodC() {
    }

    public PeriodC(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
