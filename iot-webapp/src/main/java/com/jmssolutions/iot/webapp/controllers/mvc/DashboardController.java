package com.jmssolutions.iot.webapp.controllers.mvc;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.services.DeviceManagerService;
import com.jmssolutions.iot.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by jakub on 06.01.16.
 */
@Controller
public class DashboardController {
    private final static Logger logger = Logger.getLogger(DashboardController.class);

    @Autowired
    UserService userService;
    @Autowired
    DeviceManagerService deviceService;

    @RequestMapping(value = "/dashboard")
    public String getDashboard(Model model, Principal principal){
        String name = principal.getName();
        model.addAttribute("username", name);
        return "dashboard_welcome_def";
    }

    @RequestMapping(value = "/dashboard/user")
    public String userDetails(Model model, Principal principal){
        return "dashboard_user_details_def";
    }

    @RequestMapping(value = "/dashboard/devices")
    public String devicesDetails(Model model, Principal principal){
        User owner = userService.getUserByUsername(principal.getName());
        List<Device> devices = deviceService.findDevicesByUser(owner);
        for(Device dev : devices){
            dev.setSensors(deviceService.findSensorsByDevice(dev));
        }
        model.addAttribute("deviceList", devices);
        return "dashboard_devices_def";
    }
}
