package com.ataide.corona.coronatracker.domain.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSON")
public class Person {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", length = 100)
    private String name;

    @Column(name = "PHONE", length = 11)
    private String phone;

    @ManyToOne
    @JoinColumn(name = "USER_ID", unique = true, nullable = false)
    private User user;
}
