package uz.pdp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.model.enums.Type;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data


public class Book {
    private UUID id = UUID.randomUUID();
    private String name;
    private String author;
    private double price;
    private String category;
    private Type type;
    private String bookPath;

    public Book(String name, String author, double price, String category, Type type, String bookPath) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.category = category;
        this.type = type;
        this.bookPath = bookPath;
    }
}
