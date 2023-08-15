package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.pdp.model.User;
import uz.pdp.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/mySettings", method = RequestMethod.GET)
    public String profileSettings(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");

        User userInfoById = userService.getUserInfoById(userId);

        model.addAttribute("user", userInfoById);
        return "/user/userSettings";
    }

    @RequestMapping(path = "/mySettings", method = RequestMethod.POST)
    public String editMyInfo(@ModelAttribute User user, Model model) {

        userService.editUserInfo(user);

        return "redirect:/mySettings";
    }

    //changePassword
    @RequestMapping(path = "/changePassword", method = RequestMethod.GET)
    public String changePasswordForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");

        User userInfoById = userService.getUserInfoById(userId);

        model.addAttribute("user", userInfoById);
        model.addAttribute("isChangingPassword", true);
        return "/user/userSettings";
    }

    @RequestMapping(path = "/changePassword", method = RequestMethod.POST)
    public String changePassword(Model model, HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");

        User userInfoById = userService.getUserInfoById(userId);

        if (oldPassword.equals(userInfoById.getPassword())) {
            userInfoById.setPassword(newPassword);
            userService.editUserInfo(userInfoById);
        }

        model.addAttribute("user", userInfoById);
        return "/user/userSettings";
    }

    //myBalance
    @RequestMapping(path = "/myBalance", method = RequestMethod.GET)
    public String showUserBalance(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");

        User userInfoById = userService.getUserInfoById(userId);

        model.addAttribute("user", userInfoById);
        model.addAttribute("setBalance", false);
        return "/user/userBalance";
    }

    //setBalance
    @RequestMapping(path = "/setBalance", method = RequestMethod.GET)
    public String setUserBalanceForm(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");

        User userInfoById = userService.getUserInfoById(userId);

        model.addAttribute("user", userInfoById);
        model.addAttribute("setBalance", true);
        return "/user/userBalance";
    }

    //setBalance
    @RequestMapping(path = "/setBalance", method = RequestMethod.POST)
    public String setUserBalance(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer userId = (int) session.getAttribute("userId");
        String money = request.getParameter("money");
        User userInfoById = userService.getUserInfoById(userId);

        userInfoById.setBalance(userInfoById.getBalance() + Double.parseDouble(money));
        userService.editUserInfo(userInfoById);

        model.addAttribute("user", userInfoById);
        return "/user/userBalance";
    }

}
