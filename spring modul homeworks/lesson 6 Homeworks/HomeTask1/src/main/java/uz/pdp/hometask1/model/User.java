package uz.pdp.hometask1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.index.qual.LengthOf;
import org.checkerframework.checker.units.qual.Length;
import org.checkerframework.common.value.qual.MinLen;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    @MinLen(7)
    @Column(nullable = false, unique = true)
    private String phoneNumber;

}
