package com.jmssolutions.iot.webapp.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.services.UserService;
import com.jmssolutions.iot.webapp.DTO.UserDTO;

@Controller
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerForm(Model model){
		model.addAttribute("userDTO", new UserDTO());
		return "add-user";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("userDTO") @Valid UserDTO userDTO, BindingResult result, ModelMap model){
		if(result.hasErrors()){
			logger.info("Invalid form data provided.");
			logger.info(result.getAllErrors().toString());
			return "add-user";
		}
		else{
			User user = new User();
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());;
			user.setEmail(userDTO.getEmail());
			user.setFirstName(userDTO.getFirstName());
			user.setLastName(userDTO.getLastName());
			try{
				userService.insertUser(user);
			} catch (Exception e){
				model.addAttribute("errorMessage", e.getMessage());
				model.addAttribute("stackTrace", e.getStackTrace().toString());
				return "internal-error";
			}
			
			model.addAttribute("userName", user.getUsername());
			model.addAttribute("userEmail", user.getEmail());
			
			return "user-added";
		}
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public String searchUsers(HttpServletRequest request, Model model){
        User query = new User();
        String username = "";
        String email = "";
        if(request.getParameterMap().containsKey("username"))
            username = request.getParameter("username");
        if(request.getParameterMap().containsKey("email"))
            email = request.getParameter("email");

        if(username.isEmpty() && email.isEmpty())
            model.addAttribute("usersList", userService.getAllUsers());
        else{
            query.setUsername(username);
            query.setEmail(email);
            model.addAttribute("usersList", userService.getUserByUserParams(query));
        }
		return "list-users";
	}

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String modifyUsers(@RequestParam(name = "action") String action, @RequestParam(name = "idUser") long ID){
        if(action == "remove"){
//            userService.deleteUser(userService.getUserByID(ID));
        }
        else if(action == "modify")
        {

        }
        return "err";
    }
	
	@RequestMapping(value = "/user-added")
	public String userRegistered(Model model){
		return "user-added";
	}

	

}
