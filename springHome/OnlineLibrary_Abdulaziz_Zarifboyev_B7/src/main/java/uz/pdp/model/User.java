package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "user_name")
    private String email;
    @Column(name = "password")
    private String password;

    @ManyToOne
    private Role role;

    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

}
