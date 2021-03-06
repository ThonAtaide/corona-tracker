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
@Table(name = "STORE")
public class Store {

    @Id
    @Column(name = "ID", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_SEQ")
    @SequenceGenerator(name="STORE_SEQ", sequenceName="STORE_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "PHONE", length = 11)
    private String phone;

    @Column(name = "CNPJ", length = 14, unique = true)
    private String cnpj;

    @Column(name = "CEP", length = 8)
    private String cep;

    @ManyToOne
    @JoinColumn(name = "USER_ID", unique = true, nullable = false)
    private User user;
}
