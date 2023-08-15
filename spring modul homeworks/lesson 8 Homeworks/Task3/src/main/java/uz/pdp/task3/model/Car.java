package uz.pdp.task3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.task3.model.address.Region;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String model;
    @Column(nullable = false)
    private String stateNumber;
    @Column(nullable = false)
    private Integer madeYear;
    @Column(nullable = false)
    private String type;

    @ManyToOne
    private Region region;


}
