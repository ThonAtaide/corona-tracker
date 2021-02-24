package com.ataide.corona.coronatracker.domain.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_TABLE")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", length = 32, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 32)
    private String password;

    @Column(name = "ROLE", length = 100)
    private String role;
}
