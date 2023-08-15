package uz.pdp.schoolsystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Groups group;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Student student;

    @Column
    private Double mark;







}
