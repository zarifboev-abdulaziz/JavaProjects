package uz.pdp.service.crud;

import java.io.IOException;

public interface CrudRepository {
    void menu() throws IOException;
    void create() throws IOException;
    void read();
    void update() throws IOException;
    void delete();
}
