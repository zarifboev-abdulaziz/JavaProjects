package uz.pdp.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "title")
    private String name;
    @Column(name = "description", columnDefinition = " text")
    private String description;
    @Column(name = "price", columnDefinition = " double precision default 10")
    private double price;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> authors = new ArrayList<>();


}

