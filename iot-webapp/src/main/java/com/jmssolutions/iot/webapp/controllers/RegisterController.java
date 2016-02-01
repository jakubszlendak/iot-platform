package com.jmssolutions.iot.webapp.controllers;

import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.domain.VerificationToken;
import com.jmssolutions.iot.events.OnRegisterCompleteEvent;
import com.jmssolutions.iot.exceptions.UserExistsException;
import com.jmssolutions.iot.services.UserService;
import com.jmssolutions.iot.webapp.DTO.UserDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Calendar;

/**
 * Created by jakub on 06.01.16.
 */
@Controller
public class RegisterController {

    public static final Logger logger = Logger.getLogger(RegisterController.class);

    @Autowired
    UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegister(Model model){
        logger.info("Entering /register [GET] controller");
        model.addAttribute("userDTO", new UserDTO());
        return "user_registration_def";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegister(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult result, ModelMap model, WebRequest request){
        if(result.hasErrors()){
            logger.info("Invalid data provided.");
            logger.info(result.getAllErrors().toString());
            return "user_registration_def";
        }
        else{
            User user = new User();
            user.setUsername(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());;
            user.setEmail(userDTO.getEmail());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEnabled(0);
            try{
                user = userService.createUser(user);
            } catch (UserExistsException e){
                model.addAttribute("errorMessage", "User with given username or email already exists.");
                return "user_registration_def";
            }
            try {
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegisterCompleteEvent(user, request.getLocale(), appUrl));
            } catch (Exception e){
                model.addAttribute("errorMessage", "Error when sending activation email");
                return "user_registration_def";
            }

            model.addAttribute("userName", user.getUsername());
            model.addAttribute("userEmail", user.getEmail());

            return "after_registration_def";
        }
    }

    @RequestMapping(value = "/register/confirm", method = RequestMethod.GET)
    public String registrationConfirm(@RequestParam(name = "token") String token, WebRequest request, Model model ){
        VerificationToken userToken = userService.getVerificationToken(token);
        if(userToken == null){
            model.addAttribute("errorMessage", "User with given token does not exist.");
            return "redirect:/error";
        }

        User user = userToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if(userToken.getExpirationDate().getTime() - cal.getTime().getTime() <= 0){
            model.addAttribute("errorMessage", "Verification token expired.");
            userService.deleteUser(user.getID());
            return "redirect:/error";
        }

        user.setEnabled(1);
        userService.updateUser(user);
        userService.deleteVerificationToken(userToken);
        return "after_activation_def";

    }

}
