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
        model.addAttribute("users", service.getAllUsers());
        model.addAttribute("formHead", "Add New User");
        model.addAttribute("formAction", "/users");
        model.addAttribute("user", new User());
        model.addAttribute("formButton", "Create user");
        return "usersIndex";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        System.out.println(user.toString());
        service.saveUser(user);
        return "redirect:/users";
    }

    //Edit user mode
    @GetMapping("/update/{id}")
    public String editUser(Model model, @PathVariable("id") Long id) {
        model.addAttribute("users",service.getAllUsers());
        model.addAttribute("formHead", "Edit This User");
        model.addAttribute("formAction", "/users/update/" + id);
        model.addAttribute("user", service.getUserById(id));
        model.addAttribute("formButton", "Update this user");
        return "usersIndex";
    }

    @PatchMapping()
    public String editUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/users";
    }

    //Button actions
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        service.updateUser(user);
        return "redirect:/users";
    }
}
