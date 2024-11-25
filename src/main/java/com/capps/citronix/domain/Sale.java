package com.capps.citronix.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "unit_price", nullable = false)
    private BigDecimal unitPrice;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @ManyToOne
    @JoinColumn(name = "harvest_id", nullable = false)
    private Harvest harvest;

}
