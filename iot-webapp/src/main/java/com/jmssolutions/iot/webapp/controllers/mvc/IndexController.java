package com.jmssolutions.iot.webapp.controllers.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {
	@RequestMapping("/")
	public String homepage(HttpServletRequest request){
		return "homepage_def";
	}

}
