package com.jmssolutions.iot.webapp.controllers;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jakub on 06.01.16.
 */
@Controller
public class LoginRegisterController {

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout){
        ModelAndView model = new ModelAndView();
        if(error != null){
            model.addObject("error", "Invalid username or password.");
        }
        if(logout != null){
            model.addObject("msg", "Logged out succesfully");
        }
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "403", method = RequestMethod.GET)
    public String accessDenied(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(!(authentication instanceof AnonymousAuthenticationToken)){
            UserDetails ud = (UserDetails)authentication.getPrincipal();
            model.addAttribute("username", ud.getUsername());
        }
        return "403";
    }
}
