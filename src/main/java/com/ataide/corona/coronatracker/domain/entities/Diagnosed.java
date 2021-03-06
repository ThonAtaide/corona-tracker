package com.ataide.corona.coronatracker.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DIAGNOSED")
public class Diagnosed {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DIAGNOSED_SEQ")
    @SequenceGenerator(name="DIAGNOSED_SEQ", sequenceName="DIAGNOSED_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "PHONE", length = 11)
    private String phone;

    @Column(name = "ESTIMATED_DAY_ZERO")
    private Timestamp estimatedDayZero;

    @Builder.Default
    @Column(name = "CREATED_AT")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "HEALTH_UNITY_ID", nullable = false)
    private HealthUnity healthUnity;
}
