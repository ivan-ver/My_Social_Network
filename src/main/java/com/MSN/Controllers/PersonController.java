package com.MSN.Controllers;

import com.MSN.Model.User;
import com.MSN.Model.Role;
import com.MSN.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Controller
public class PersonController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String welcome(Map<String,Object> model){
        Iterable<User> users = userRepo.findAll();
        model.put("users",users);
        return "welcome_page";
    }


    @GetMapping("/main")
    public String main(Map<String,Object> model){
        Iterable<User> persons = userRepo.findAll();
        model.put("persons",persons);
        return "main";
    }

    @GetMapping("/user/{id}")
    public String getPerson(@PathVariable("id") Integer id,
                            Map<String,Object> model){
        Optional<User> person = userRepo.findById(id);
        model.put("user",person.get());
        return "person_page";
    }

    @PostMapping
    public String add(@RequestParam String name,
                      @RequestParam String lustName,
                      @RequestParam String email,
                      @RequestParam String city,
                      @RequestParam String birthDay,
                      @RequestParam String password){

        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date birth_day = null;
        try {
            birth_day = format.parse(birthDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = new User(name
                ,lustName
                ,email
                ,birth_day
                ,city
                ,password
                ,true
                ,Collections.singleton(Role.USER));

        userRepo.save(user);
        System.out.println("");
        return "redirect:";
    }


    @RequestMapping(value = "/userInfo", method = RequestMethod.GET)
    public String userInfo(Model model, Principal principal) {

        // After user login successfully.
        String userName = principal.getName();


        System.out.println("User Name: " + userName);

        org.springframework.security.core.userdetails.User loginedUser = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
//
//        String userInfo = WebUtils.toString(loginedUser);
//        model.addAttribute("userInfo", userInfo);

        return "userInfoPage";
    }
}
