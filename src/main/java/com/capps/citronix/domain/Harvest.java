package com.capps.citronix.domain;

import com.capps.citronix.domain.enums.Saison;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Harvest {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "total_quantity", nullable = false)
    private float totalQuantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Saison saison;

    @OneToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    @OneToMany(mappedBy = "harvest", cascade = CascadeType.ALL)
    private List<HarvestDetails> harvestDetails = new ArrayList<>();

}
