package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.dto.CommentDto;
import uz.pdp.dto.CourseDto;
import uz.pdp.dto.LessonDto;
import uz.pdp.model.LearningMaterial;
import uz.pdp.model.Lesson;
import uz.pdp.model.Module;
import uz.pdp.model.Task;
import uz.pdp.service.CourseService;
import uz.pdp.service.LessonAttributeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class StudentCourseController {
    @Autowired
    CourseService courseService;
    @Autowired
    LessonAttributeService lessonAttributeService;

    //Modules
    @RequestMapping(path = "/myModules/{courseId}", method = RequestMethod.GET)
    public String getMyCourseModules(@PathVariable Integer courseId, Model model, HttpServletRequest request){
        List<Module> courseModules = courseService.getCourseModules(courseId);
        CourseDto allAboutCourse = courseService.getAllAboutCourse(courseId);
        HttpSession session = request.getSession();
        session.setAttribute("lastCourseId", courseId);

        model.addAttribute("courseModules", courseModules);
        model.addAttribute("mentors", allAboutCourse.getMentors());
        return "/student/courseModules";
    }


    //Tasks
    @RequestMapping(path = "/myLessons/{moduleId}", method = RequestMethod.GET)
    public String getMyLessons(@PathVariable Integer moduleId, Model model, HttpServletRequest request){
        List<LessonDto> myLessons = courseService.getLessons(moduleId);
        HttpSession session = request.getSession();
        session.setAttribute("lastModuleId", moduleId);
        Integer lastCourseId = (int)session.getAttribute("lastCourseId");

        model.addAttribute("lastCourseId", lastCourseId);
        model.addAttribute("myLessons", myLessons);
        return "/student/myLessons";
    }

    //myLearningMaterials
    @RequestMapping(path = "/myLearningMaterials/{lessonId}", method = RequestMethod.GET)
    public String getLearningMaterials(@PathVariable Integer lessonId, Model model, HttpServletRequest request){
        List<LearningMaterial> myLearningMaterialList = courseService.getMyLearningMaterials(lessonId);
        HttpSession session = request.getSession();
        Integer lastModuleId = (int)session.getAttribute("lastModuleId");

        model.addAttribute("lastModuleId", lastModuleId);
        model.addAttribute("myLearningMaterialList", myLearningMaterialList);
        return "/student/myLearningMaterials";
    }

    //Tasks
    @RequestMapping(path = "/myTasks/{lessonId}", method = RequestMethod.GET)
    public String getTasks(@PathVariable Integer lessonId, Model model, HttpServletRequest request){
        List<Task> myTaskList = courseService.getMyTasks(lessonId);
        HttpSession session = request.getSession();
        Integer lastModuleId = (int)session.getAttribute("lastModuleId");
        Integer numberOfLikes = lessonAttributeService.getNumberOfLikes(lessonId);
        List<CommentDto> commentDtoList = lessonAttributeService.getAllComments(lessonId);

        model.addAttribute("numberOfLikes", numberOfLikes);
        model.addAttribute("lastModuleId", lastModuleId);
        model.addAttribute("myTaskList", myTaskList);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("commentList", commentDtoList);
        return "/student/myTaskList";
    }

}
