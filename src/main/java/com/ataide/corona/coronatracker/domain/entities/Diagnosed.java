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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(targetEntity = HealthUnity.class)
    private HealthUnity store;
}
