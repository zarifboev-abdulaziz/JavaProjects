package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CourseDao;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Author;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;

    public List<CourseDto> getAllCourses(Integer page){
        return courseDao.getCourses(page);
    }


    public void saveCourse(CourseDto courseDto, String[] authors) {

        List<Integer> authorIdsList = new ArrayList<>();

        for (String author : authors) {
            authorIdsList.add(Integer.parseInt(author));
        }

        courseDao.saveCourseToDb(courseDto, authorIdsList);
    }

    public CourseDto getCourseById(Integer id) {
        CourseDto courseDtoById = courseDao.getCourseByIdFromDb(id);

        if (courseDtoById != null){
            return courseDtoById;
        }
        return null;
    }

    public void deleteCourseById(Integer id) {
        courseDao.deleteCourseByIdFromDb(id);
    }

    public Author getAuthorById(Integer id) {
        Author author = courseDao.getAuthorByIdFromDb(id);
        return author;
    }

    public List<CourseDto> getAuthorCourses(Integer id) {
        List<CourseDto> courseDtoList = courseDao.getAuthorCoursesFromDb(id);
        return courseDtoList;
    }

    public List<Author> getAllAuthors() {
        List<Author> authorList = courseDao.getAllAuthorsFromDb();
        return authorList;
    }

    public List<CourseDto> getAllCoursesBySearch(String search) {
        return courseDao.getCoursesBySearch(search);
    }

    public int getTotalNumberOfCourses() {
        return courseDao.getTotalCourse();
    }
}
