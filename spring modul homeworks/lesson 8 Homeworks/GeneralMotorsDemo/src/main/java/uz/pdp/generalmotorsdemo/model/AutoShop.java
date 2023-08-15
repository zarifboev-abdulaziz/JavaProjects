package uz.pdp.generalmotorsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.generalmotorsdemo.model.address.Address;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class AutoShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private GMFactory gmFactory;

    @OneToOne
    private Address address;

    @OneToMany
    private List<Car> cars;


}
