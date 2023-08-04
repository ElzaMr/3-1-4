package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/users")
    public String getAllUsers(Principal principal, Model model) {
        List<User> list = userService.getAllUsers();
        model.addAttribute("listOfUsers", list);
        model.addAttribute("admin", userService.getUserByUsername(principal.getName()));
        List<Role> listOfRoles = roleService.getAllRoles();
        model.addAttribute("listOfRoles", listOfRoles);
        return "ADMIN/users";
    }

    @GetMapping(value = "/adminUser")
    public String getAdminUser(Principal principal, Model model) {
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        return "ADMIN/adminPage";
    }

    @GetMapping(value = "/new")
    public String newUser(User user, Model model, Principal principal) {
        model.addAttribute("user1", user);
        model.addAttribute("admin", userService.getUserByUsername(principal.getName()));
        List<Role> listOfRoles = roleService.getAllRoles();
        model.addAttribute("listOfRoles", listOfRoles);
        return "ADMIN/new";
    }

    @PostMapping(value = "/users")
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/new";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @PatchMapping(value = "/{id}")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:users";
    }

    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
