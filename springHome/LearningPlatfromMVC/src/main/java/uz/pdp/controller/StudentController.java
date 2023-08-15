package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.ModuleDto;
import uz.pdp.model.Course;
import uz.pdp.model.Result;
import uz.pdp.service.CourseService;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentController {
    @Autowired
    CourseService courseService;


    @RequestMapping(path = "/studentHome", method = RequestMethod.GET)
    public String studentHome(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Integer userId = (Integer)session.getAttribute("userId");
        if (userId == null){
            return "home";
        }

        String message = request.getParameter("message");
        if (message == null){
            model.addAttribute("message", "Welcome to home page");
        }else {
            model.addAttribute("message", message);
        }
        return "/student/studentHome";
    }

    @RequestMapping(path = "/activeCourses", method = RequestMethod.GET)
    public String getActiveCourses(Model model){
        List<Course> courseList = courseService.getActiveCourses();

        model.addAttribute("courseList", courseList);
        return "/courses/view-courses";
    }

    @RequestMapping(path = "/courseInfo/{id}", method = RequestMethod.GET)
    public String getCourseInfo(@PathVariable Integer id, Model model, HttpServletRequest request){
        CourseDto courseDto = courseService.getAllAboutCourse(id);
        int countLessons = 0;
        for (ModuleDto module : courseDto.getModules()) {
            countLessons += module.getLessons().size();
        }
        HttpSession session = request.getSession();
        Integer userId = (int)session.getAttribute("userId");
        Boolean isCoursePurchased = false;
        if (userId != null){
             isCoursePurchased = courseService.isCoursePurchased(id, userId);
        }

        model.addAttribute("selectedCourse", courseDto);
        model.addAttribute("countLesson", countLessons);
        model.addAttribute("isCoursePurchased", isCoursePurchased);
        return "/courses/courseInfo";
    }

    @RequestMapping(path = "/studentPurchase/{courseId}", method = RequestMethod.GET)
    public String studentPurchaseCourse(@PathVariable Integer courseId, Model model, HttpServletRequest request){
        Result result = courseService.studentPurchaseCourse(courseId, request);

        if (result.isSuccess()){
            model.addAttribute("message", result.getMessage());
            return "redirect:/studentHome";
        }
        model.addAttribute("message", result.getMessage());
        return "redirect:/studentHome";
    }

    @RequestMapping(path = "/myCourses", method = RequestMethod.GET)
    public String getMyCourses(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (int)session.getAttribute("userId");


        List<Course> courseList = courseService.getMyCourses(userId);

        model.addAttribute("myCourseList", courseList);
        return "/student/myCourses";
    }


}
