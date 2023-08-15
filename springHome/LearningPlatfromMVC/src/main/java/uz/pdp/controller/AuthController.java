package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.model.User;
import uz.pdp.service.AuthService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthController {
    @Autowired
    AuthService authService;

    //REGISTRATION
    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String registerForm(Model model){
        model.addAttribute("message", "Register form");
        return "/auth/registerForm";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute User user, Model model, HttpServletRequest request){
        Integer userId = authService.registerUser(user);

        if (userId == null) {
            model.addAttribute("message", "Registration Failed PLease try again");
            return "/auth/registerForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", userId);
        return "redirect:/studentHome";
    }


    //LOGIN
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String loginForm(Model model){
        model.addAttribute("message", "Login Form");
        return "/auth/loginForm";
    }

    // TODO: 2/12/2022
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user, Model model, HttpServletRequest request){
        User loginUser = authService.loginUser(user);

        if (loginUser.getId() == null) {
            model.addAttribute("message", "Login Process Failed, PLease try again");
            return "/auth/registerForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("userId", loginUser.getId());

        if (loginUser.getRoleId() == 1){
            return "redirect:/studentHome";
        }
        return "";
    }


    @RequestMapping(path = "/logout", method = RequestMethod.GET)
    public String login(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("userId", null);
        session.invalidate();
        return "home";
    }

}
