package com.jmssolutions.iot.webapp.controllers.api;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.services.DeviceManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jakub on 21.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:WEB-INF/api-dispatcher-servlet.xml", "classpath:services-context.xml"})
@WebAppConfiguration
public class DeviceSensorApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private DeviceManagerService deviceManagerService;


    @Test
    public void getAllDevicesAsJSON_ShouldReturnAllUserDevicesAsJSON() throws Exception {
        User owner = new User();
        owner.setEmail("xxx");
        owner.setUsername("zzz");
        Device first = new Device();
        first.setProducerName("aaa");
        first.setName("bbb");
        first.setID(1);
        first.setAesKey("xxx");
        first.setOwner(owner);
        Device second = new Device();
        second.setProducerName("aa22a");
        second.setName("bb2b");
        second.setID(2);
        second.setAesKey("2xxx");
        second.setOwner(owner);

        when(deviceManagerService.findDevicesByUser(owner)).thenReturn(Arrays.asList(first, second));
        mockMvc.perform(get("/api/device").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].producerName", is("aaa")))
                .andExpect(jsonPath("$[0].name", is("bbb")))
                .andExpect(jsonPath("$[0].owner", is(owner)));
        verify(deviceManagerService, times(1)).findDevicesByUser(owner);
        verifyNoMoreInteractions(deviceManagerService);
    }
}