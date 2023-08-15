package uz.pdp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.LessonDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.*;

import java.util.List;

@Component
public class CourseDao {
    @Autowired
    JdbcTemplate template;

    public List<Course> getActiveCoursesFromDb() {
        String query = "Select * from courses";

        List<Course> courseList = template.query(query, (resultSet, rowNum) -> {
            Course course = new Course();
            course.setId(resultSet.getInt(1));
            course.setTitle(resultSet.getString(2));
            course.setDescription(resultSet.getString(3));
            course.setPrice(resultSet.getDouble(4));
            return course;
        });

        return courseList;
    }

    public CourseDto getCourseFromDb(Integer courseId) {
        String query = "Select * from courses where id = " + courseId;

                CourseDto outputCourseDto = template.queryForObject(query, (resultSet, rowNum) -> {
                CourseDto courseDto = new CourseDto();
                courseDto.setId(resultSet.getInt(1));
                courseDto.setTitle(resultSet.getString(2));
                courseDto.setDescription(resultSet.getString(3));
                courseDto.setPrice(resultSet.getDouble(4));

                String sql = "Select * From modules where course_id = " + courseDto.getId() +
                        " order by order_number";

                List<ModuleDto> moduleDtoList = template.query(sql, (resultSet1, rowNum1) -> {
                    ModuleDto moduleDto = new ModuleDto();

                    moduleDto.setId(resultSet1.getInt(1));
                    moduleDto.setCourseId(resultSet1.getInt(2));
                    moduleDto.setTitle(resultSet1.getString(3));
                    moduleDto.setOrderNumber(resultSet1.getInt(4));

                    String sql1 =
                            "Select * from lessons where modul_id = " + moduleDto.getId()+ " order by order_number";

                    List<LessonDto> lessonDtoList = template.query(sql1, (resultSet2, rowNum2) -> {
                        LessonDto lessonDto = new LessonDto();
                        lessonDto.setId(resultSet2.getInt(1));
                        lessonDto.setModuleId(resultSet2.getInt(2));
                        lessonDto.setTitle(resultSet2.getString(3));
                        lessonDto.setOrderNumber(resultSet2.getInt(4));

                        return lessonDto;
                    });

                    moduleDto.setLessons(lessonDtoList);
                    return moduleDto;
                });

                String queryForMentor = "select * from users u where u.id IN (\n" +
                        "    select mc.user_id\n" +
                        "    from mentors_courses mc\n" +
                        "    where mc.course_id = "+courseId+")";

                List<User> mentorList = template.query(queryForMentor, (rtSetForMentor, rNum1) -> {
                    User mentor = new User();

                    mentor.setId(rtSetForMentor.getInt(1));
                    mentor.setFirstName(rtSetForMentor.getString(2));
                    mentor.setLastName(rtSetForMentor.getString(3));
                    mentor.setEmail(rtSetForMentor.getString(4));
                    mentor.setPassword(rtSetForMentor.getString(5));
                    mentor.setRoleId(rtSetForMentor.getInt(6));
                    mentor.setBalance(rtSetForMentor.getDouble(7));

                    return mentor;
                });


                courseDto.setMentors(mentorList);
                courseDto.setModules(moduleDtoList);
                return courseDto;
            });

            return outputCourseDto;
    }

    public Course getCourseById(Integer courseId) {
        String query = "select * from courses where id = " + courseId;

        Course course = template.queryForObject(query, (resultSet, rowNum) -> {
            Course courseFromDb = new Course();
            courseFromDb.setId(resultSet.getInt(1));
            courseFromDb.setTitle(resultSet.getString(2));
            courseFromDb.setDescription(resultSet.getString(3));
            courseFromDb.setPrice(resultSet.getDouble(4));
            return courseFromDb;
        });
        return course;
    }

    public boolean purchaseCourseForStudent(Integer studentId, Integer courseId) {
        String query = "Select * from student_purchase_course("+studentId+", "+courseId+")";

        Boolean isPurchased = template.queryForObject(query, (rs, rowNum) -> rs.getBoolean(1));

        return isPurchased;
    }

    public Boolean isCoursePurchased(Integer courseId, Integer userId) {
        String query = "select course_id from students_courses where user_id = " + userId;

        List<Course> studentCourses = template.query(query, (resultSet, rowNum) -> {
            Course courseFromDb = new Course();
            courseFromDb.setId(resultSet.getInt(1));

            return courseFromDb;
        });

        for (Course studentCourse : studentCourses) {
            if (studentCourse.getId() == courseId){
                return true;
            }
        }
        return false;
    }

    public List<Course> getMyCoursesFromDb(Integer userId) {
        String query = "select *\n" +
                "from courses c where c.id IN (\n" +
                "    select sc.course_id\n" +
                "    from students_courses sc where sc.user_id = "+userId+" );";

        List<Course> myCourseList = template.query(query, (resultSet, rowNum) -> {
            Course course = new Course();
            course.setId(resultSet.getInt(1));
            course.setTitle(resultSet.getString(2));
            course.setDescription(resultSet.getString(3));
            course.setPrice(resultSet.getDouble(4));
            return course;
        });

        return myCourseList;
    }

    public List<Module> getModulesOfCourse(Integer courseId) {
        String query = "Select * from modules where course_id = "+courseId+" order by order_number";

        List<Module> courseModules = template.query(query, (resultSet, rowNum) -> {
            Module module = new Module();
            module.setId(resultSet.getInt(1));
            module.setCourseId(resultSet.getInt(2));
            module.setTitle(resultSet.getString(3));
            module.setOrderNumber(resultSet.getInt(4));

            return module;
        });

        return courseModules;
    }

    public List<LessonDto> getStudentLessons(Integer moduleId) {
        String query = "Select * from lessons where modul_id = "+moduleId+" order by order_number";

        List<LessonDto> myLessons = template.query(query, (resultSet, rowNum) -> {
            LessonDto lessonDto = new LessonDto();
            lessonDto.setId(resultSet.getInt(1));
            lessonDto.setModuleId(resultSet.getInt(2));
            lessonDto.setTitle(resultSet.getString(3));
            lessonDto.setOrderNumber(resultSet.getInt(4));

            return lessonDto;
        });

        return myLessons;
    }

    public List<LearningMaterial> getMyLearningMaterialsFromDb(Integer lessonId) {
        String queryToGetLearningMaterials = "Select * from learning_materials where " +
                "lesson_id = "+lessonId+" order by order_number";

        List<LearningMaterial> learningMaterialList =
                template.query(queryToGetLearningMaterials, (rtSet, rNum1) -> {
                    LearningMaterial learningMaterial = new LearningMaterial();
                    learningMaterial.setId(rtSet.getInt(1));
                    learningMaterial.setLessonId(rtSet.getInt(2));
                    learningMaterial.setTitle(rtSet.getString(3));
                    learningMaterial.setInformation(rtSet.getString(4));
                    learningMaterial.setOrderNumber(rtSet.getInt(5));

                    return learningMaterial;
                });
        return learningMaterialList;
    }

    public List<Task> getMyTasksFromDb(Integer lessonId) {
        String queryForTasks = "Select * from tasks where lesson_id = "+lessonId+
                " order by order_number";

        List<Task> taskList = template.query(queryForTasks, (rtSet, rNum2) -> {
            Task task = new Task();
            task.setId(rtSet.getInt(1));
            task.setLessonId(rtSet.getInt(2));
            task.setTitle(rtSet.getString(3));
            task.setBody(rtSet.getString(4));
            task.setOrderNumber(rtSet.getInt(5));

            return task;
        });

       return taskList;
    }


    public List<Course> getInfoAboutMentor(Integer mentorId) {
        String queryForMentorCourses = "select * from courses c\n" +
                "where c.id IN (\n" +
                "    select mc.course_id\n" +
                "    from mentors_courses mc\n" +
                "    where mc.user_id = "+mentorId+" \n" +
                ")";

        List<Course> courseList = template.query(queryForMentorCourses, (resultSet, rowNum) -> {
            Course course = new Course();
            course.setId(resultSet.getInt(1));
            course.setTitle(resultSet.getString(2));
            course.setDescription(resultSet.getString(3));
            course.setPrice(resultSet.getDouble(4));
            return course;
        });
        return courseList;
    }
}
