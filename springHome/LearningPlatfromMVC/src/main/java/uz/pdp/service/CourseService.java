package uz.pdp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.dao.CourseDao;
import uz.pdp.dao.UserDao;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.LessonDto;
import uz.pdp.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseDao courseDao;
    @Autowired
    UserDao userDao;


    public List<Course> getActiveCourses() {
        List<Course> courseList = courseDao.getActiveCoursesFromDb();

        return courseList;
    }

    public CourseDto getAllAboutCourse(Integer id) {
        CourseDto courseDto = courseDao.getCourseFromDb(id);
        return courseDto;
    }

    public Result studentPurchaseCourse(Integer courseId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int)session.getAttribute("userId");

        Course course = courseDao.getCourseById(courseId);
        User purchasingStudent = userDao.getUserById(userId);

        if (course.getId() == null){
            return new Result(false, "Course not found");
        } else if (course.getPrice() > purchasingStudent.getBalance()){
            return new Result(false, "The money in your balance is not enough to purchase this course");
        }

        boolean isPurchased = courseDao.purchaseCourseForStudent(userId, course.getId());

        if (isPurchased){
            return new Result(isPurchased, "Successfully Purchased, You can see the course in \"My Courses\" section");
        }
        return new Result(isPurchased, "Failed to purchase, Please try again!");
    }

    public Boolean isCoursePurchased(Integer courseId, Integer userId) {
       return courseDao.isCoursePurchased(courseId, userId);
    }

    public List<Course> getMyCourses(Integer userId) {
        return courseDao.getMyCoursesFromDb(userId);
    }

    public List<Module> getCourseModules(Integer courseId) {
        return courseDao.getModulesOfCourse(courseId);
    }

    public List<LessonDto> getLessons(Integer moduleId) {
        return courseDao.getStudentLessons(moduleId);
    }

    public List<LearningMaterial> getMyLearningMaterials(Integer lessonId) {
        return courseDao.getMyLearningMaterialsFromDb(lessonId);
    }

    public List<Task> getMyTasks(Integer lessonId) {
        return courseDao.getMyTasksFromDb(lessonId);
    }

    public User getInfoAboutMentor(Integer mentorId) {
        return userDao.getUserById(mentorId);
    }

    public List<Course> getMentorCourses(Integer mentorId) {
        return courseDao.getInfoAboutMentor(mentorId);
    }
}
