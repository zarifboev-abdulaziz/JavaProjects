package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import uz.pdp.dto.CourseDto;
import uz.pdp.model.Author;
import uz.pdp.service.CourseService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    //READ
    @RequestMapping(path = "/courses/{page}", method = RequestMethod.GET)
    public String getAllCourses(@PathVariable Integer page,Model model){
        if (page == null){
            page = 1;
        }
        List<CourseDto> allCourses = courseService.getAllCourses(page);
        int totalNumber = courseService.getTotalNumberOfCourses();

        if (totalNumber % 5 != 0){
            totalNumber = totalNumber/5 + 1;
        } else {
            totalNumber = totalNumber/5;
        }

        model.addAttribute("totalPage", totalNumber);
        model.addAttribute("courseList", allCourses);
        return "courses/view-courses";
    }

    //READ
    @RequestMapping(path = "/courses/search", method = RequestMethod.POST)
    public String getCoursesBySearch(Model model, HttpServletRequest request){
        String search = request.getParameter("search");
        List<CourseDto> allCoursesBySearch = courseService.getAllCoursesBySearch(search);

        model.addAttribute("courseList", allCoursesBySearch);
        return "courses/view-courses";
    }

    //CREATE (part 1)
    @RequestMapping("/courses/addForm")
    public String addCourseForm(Model model){
        List<Author> authorList = courseService.getAllAuthors();

        model.addAttribute("authorList", authorList);
        return "/courses/add-course-form";
    }


    //CREATE (part 2)
    @RequestMapping(path = "/courses", method = RequestMethod.POST)
    public RedirectView addCourse(HttpServletRequest request){
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] authors = request.getParameterValues("authors");

        CourseDto courseDto = new CourseDto();
        courseDto.setName(name);
        courseDto.setDescription(description);

        courseService.saveCourse(courseDto, authors);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/courses/1");
        return redirectView;
    }

    //UPDATE
    @RequestMapping("/courses/editForm/{id}")
    public String editCourseForm(Model model, @PathVariable Integer id){
        CourseDto courseDto = courseService.getCourseById(id);

        if (courseDto != null){
            model.addAttribute("selectedCourse", courseDto);
            return "courses/edit-course-form";
        } else {
            return "redirect:/courses/1";
        }
    }

    //DELETE
    @RequestMapping(path = "/courses/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(Model model, @PathVariable Integer id){
        courseService.deleteCourseById(id);
        return "redirect:/courses/1";
    }

    //GET INFORMATION ABOUT COURSE
    @RequestMapping(path = "/courses/info/{id}", method = RequestMethod.GET)
    public String infoAboutCourse(Model model, @PathVariable Integer id){
        CourseDto courseDto = courseService.getCourseById(id);
        model.addAttribute("selectedCourse", courseDto);
        model.addAttribute("authors", courseDto.getAuthors());
        return "info-about-courses";
    }

    //GET INFORMATION ABOUT AUTHOR
    @RequestMapping(path = "/courses/author/{id}", method = RequestMethod.GET)
    public String infoAboutAuthor(Model model, @PathVariable Integer id){
        Author author = courseService.getAuthorById(id);
        List<CourseDto> authorCourses = courseService.getAuthorCourses(id);

        model.addAttribute("selectedAuthor", author);
        model.addAttribute("authorCourses", authorCourses);
        return "info-about-author";
    }


}
