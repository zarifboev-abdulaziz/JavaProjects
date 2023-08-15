package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.dto.CommentDto;
import uz.pdp.service.LessonAttributeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LessonAttributeController {
    @Autowired
    LessonAttributeService lessonAttributeService;

    @RequestMapping(path = "/likeButton/{lessonId}", method = RequestMethod.GET)
    public String likePressed(@PathVariable Integer lessonId, HttpServletRequest request){
        HttpSession session = request.getSession();
        Integer userId = (int)session.getAttribute("userId");
        lessonAttributeService.likeButtonPressed(lessonId, userId);

        return "redirect:/myTasks/"+lessonId;
    }

    @RequestMapping(path = "/addComment/{lessonId}", method = RequestMethod.POST)
    public String addComment(@PathVariable Integer lessonId, HttpServletRequest request){
        HttpSession session = request.getSession();
        String comment = request.getParameter("comment");
        Integer userId = (int)session.getAttribute("userId");

        CommentDto commentDto = new CommentDto();
        commentDto.setBody(comment);
        commentDto.setLessonId(lessonId);
        commentDto.setUserId(userId);


        lessonAttributeService.addCommentToLesson(commentDto);

        return "redirect:/myTasks/"+lessonId;
    }




}
