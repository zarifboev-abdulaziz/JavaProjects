package uz.pdp.fileuploadtask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity()
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Double price;

    private Integer quantity;

    @OneToOne
    private Attachment attachment;

    @Transient
    private MultipartFile multipartFile;


}
