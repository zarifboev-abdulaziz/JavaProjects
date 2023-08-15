package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.model.Course;
import uz.pdp.model.User;
import uz.pdp.service.CourseService;

import java.util.List;

@Controller
public class MentorController {
    @Autowired
    CourseService courseService;



    @RequestMapping(path = "/mentorInfo/{mentorId}", method = RequestMethod.GET)
    public String getInfoAboutMentor(@PathVariable Integer mentorId, Model model){
        User mentor = courseService.getInfoAboutMentor(mentorId);
        List<Course> courseList = courseService.getMentorCourses(mentorId);

        model.addAttribute("selectedMentor", mentor);
        model.addAttribute("mentorCourses", courseList);
        return "/mentor/mentorInfo";
    }
}
