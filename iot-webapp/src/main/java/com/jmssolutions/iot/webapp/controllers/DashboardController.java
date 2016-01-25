package com.jmssolutions.iot.webapp.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * Created by jakub on 06.01.16.
 */
@Controller
public class DashboardController {
    private final static Logger logger = Logger.getLogger(DashboardController.class);

    @RequestMapping(value = "/dashboard")
    public String getDashboard(Model model, Principal principal){
        String name = principal.getName();
        model.addAttribute("username", name);
        return "dashboard_welcome_def";
    }
}
