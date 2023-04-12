package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        System.out.println(user.toString());
        service.saveUser(user);
        return "redirect:/users";
    }
    @GetMapping()
    public String printUser(ModelMap model, String newFormHead) {
        if (newFormHead == null) newFormHead  = "Add User";
        model.addAttribute("formHead", newFormHead);
        model.addAttribute("users", service.getAllUsers());
        return "usersIndex";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        System.out.println(id);
        service.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println(user.getId());
        service.updateUser(user);
        return "redirect:/users";
    }
}
