package com.ataide.corona.coronatracker.domain.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HEALTH_UNITY")
public class HealthUnity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "CEP", length = 8)
    private String cep;

    @Column(name = "ZONE", length = 200)
    private String zone;

    @ManyToOne
    @JoinColumn(name = "USER_ID", unique = true, nullable = false)
    private User user;
}
