package com.jmssolutions.iot.webapp.controllers;

import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.exceptions.UserExistsException;
import com.jmssolutions.iot.services.UserService;
import com.jmssolutions.iot.webapp.DTO.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;

/**
 * Created by jakub on 06.01.16.
 */
@Controller
public class RegisterController {

    public static final Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model){
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult result, ModelMap model){
        if(result.hasErrors()){
            logger.info("Invalid data provided.");
            logger.info(result.getAllErrors().toString());
            return "register";
        }
        else{
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());;
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            try{
                userService.createUser(user);
            } catch (UserExistsException e){
                model.addAttribute("errorMessage", "User with given username or email already exists.");
                return "register";
            }

            model.addAttribute("userName", user.getUsername());
            model.addAttribute("userEmail", user.getEmail());

            return "user-added";
        }
    }
}
