package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.AuthorDao;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Author;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    AuthorDao authorDao;

    public List<Author> getAllAuthors() {
        return authorDao.getAuthors();
    }

    public void saveAuthor(Author author) {
        authorDao.saveAuthorToDb(author);
    }

    public void deleteCourseById(Integer id) {
        authorDao.deleteAuthorByIdFromDb(id);
    }
}
