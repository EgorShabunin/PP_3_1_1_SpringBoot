package web.controller;

import web.model.User;
import web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired()
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        List<User> user = userService.allUsers();
        model.addAttribute("userList", user);
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "editPage";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user) {
        userService.edit(user);
        return "redirect:/users";
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
