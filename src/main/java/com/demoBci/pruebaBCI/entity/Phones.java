package com.demoBci.pruebaBCI.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "telefonos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Phones {
    @Id
    @SequenceGenerator(
            name = "phone_sequence",
            sequenceName = "phone_sequence",
            allocationSize = 1

    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "phone_sequence"
    )
    @Column(name = "id")
    private long id;
    @Column(name = "number")
    private String number;
    @Column(name = "citycode")
    private String citycode;
    @Column(name = "contrycode")
    private String contrycode;
    @Column(name = "userId")
    private String userId;
}
