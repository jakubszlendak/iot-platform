package com.jmssolutions.iot.webapp.controllers.mvc;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.services.UserService;

@Controller
public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
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
