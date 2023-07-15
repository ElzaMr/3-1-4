package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.sax.SAXResult;
import java.util.List;

@Controller
//@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String getAllUsers(Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("listOfUsers", list);
        return "users";
    }

    @GetMapping(value = "/user/{id}")//вход на страничку юзера
    public String getUser(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("listOfUser", user);
        return "user";
    }
///////////////////////////////////////////////////////////////
    @GetMapping(value = "/new")//создаем нового юзера
    public String newUser(User user, Model model) {
        model.addAttribute("user1", user);
        return "new";
    }

    @PostMapping(value = "/users")
    public String create(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "redirect:users";
    }
///////////////////////////////////////////////////////////////////////
    @GetMapping(value = "/{id}/update")
    public String update(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("user", userService.getUserById(id));
        return "update";
    }

    @PatchMapping(value = "/{id}")
    public String update1(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/users";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
//    @GetMapping(value ="/user")//нужно найти юзера по id  и вывести его страничку
//    public String userPage(User user,Model model) {
//        return "/user";
//    }
}
