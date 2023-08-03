package com.novo.controllers;

import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Slf4j
@Controller
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers(Model model){
        List<User> usersList = userService.findAll();
        model.addAttribute("fpath", "fragments/engineer");
        model.addAttribute("fname", "engineers");
        model.addAttribute("usersList", usersList);
        return "index";
    }


//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("add")
    public String getAddUser (Model model){
        model.addAttribute("fpath", "fragments/engineer-add");
        model.addAttribute("fname", "add");
        model.addAttribute("responseClass", "form-control");
        model.addAttribute("newUser", new User());
        return  "index";
    }


//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("add")
    public String postAddUser(@ModelAttribute User user, RedirectAttributes redirectAttributes){
        if(userService.existsByUsername(user.getUsername())){
            redirectAttributes.addFlashAttribute("show", true);
            redirectAttributes.addFlashAttribute("serverMessage", String.format("Пользователь с логином \"%s\" уже существует!", user.getUsername()));
            return "redirect:/users/add";
        } if(!user.getPassword().equals(user.getConfirm())){
            redirectAttributes.addFlashAttribute("show", true);
            redirectAttributes.addFlashAttribute("serverMessage", String.format("Введеные пароли не совпадают!", user.getUsername()));
            return "redirect:/users/add";
        }
        userService.saveOrdinaryUser(user);
        return "redirect:/users";
    }


    @GetMapping("delete")
    public String deleteById (@RequestParam ("id") int id){
        userService.deleteById(id);
        return "redirect:/users";
    }
}
