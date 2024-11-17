package com.capps.citronix.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tree {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "planting_date", nullable = false)
    private LocalDate plantingDate;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "field_id", nullable = false)
    private Field field;

    // Méthodes calculées (exemple : age et productivité annuelle)
    public int getAge() {
        return LocalDate.now().getYear() - plantingDate.getYear();
    }

    // Getters and setters
}
