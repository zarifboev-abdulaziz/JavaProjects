package uz.pdp.hometask2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity


public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String surname;

    @Column(nullable = false)
    Date birthDate;

    @Column(nullable = false)
    Date issuedDate;

    @Column(nullable = false, unique = true)
    String passportId;

    @Column(nullable = false)
    String country;

    @Column(nullable = false)
    String region;



}
