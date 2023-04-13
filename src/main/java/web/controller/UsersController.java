package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public String printUsers(ModelMap model) {
        model.addAttribute("formAction", "/users");
        model.addAttribute("newUser", new User(0L,"Enter Name", "Enter Last Name", 0));
        model.addAttribute("formButton", "Add User");
        model.addAttribute("users", service.getAllUsers());
        return "usersIndex";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update/{id}")
    public String updateUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("formAction", "/users/update/" + id);
        model.addAttribute("newUser", service.getUserById(id));
        model.addAttribute("formButton", "Edit User");
        model.addAttribute("users", service.getAllUsers());
        return "usersIndex";
    }
    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("newUser") User user) {
        service.updateUser(user);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}
