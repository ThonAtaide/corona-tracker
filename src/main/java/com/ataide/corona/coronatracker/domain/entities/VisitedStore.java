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
@Table(name = "VISITED_STORE")
public class VisitedStore {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "PHONE", length = 11, unique = true)
    private String phone;

    @Column(name = "CEP", length = 8)
    private String cep;

    @Builder.Default
    @Column(name = "CREATED_AT")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @ManyToOne
    @JoinColumn(name = "STORE_ID", unique = true, nullable = false)
    private Store store;
}
