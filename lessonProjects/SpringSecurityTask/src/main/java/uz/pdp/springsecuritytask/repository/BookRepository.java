package uz.pdp.springsecuritytask.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springsecuritytask.model.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
