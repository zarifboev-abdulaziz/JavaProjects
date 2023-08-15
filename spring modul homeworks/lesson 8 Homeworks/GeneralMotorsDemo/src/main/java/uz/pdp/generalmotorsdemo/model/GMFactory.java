package uz.pdp.generalmotorsdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.generalmotorsdemo.model.address.Address;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class GMFactory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String corpName;
    @Column(nullable = false)
    private String directorName;

    @OneToOne
    private Address address;


}
