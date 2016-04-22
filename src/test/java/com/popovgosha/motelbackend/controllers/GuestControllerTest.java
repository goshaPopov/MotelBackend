package com.popovgosha.motelbackend.controllers;

import com.popovgosha.motelbackend.config.WebConfig;
import com.popovgosha.motelbackend.domain.Guest;
import com.popovgosha.motelbackend.services.GuestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class GuestControllerTest {

    private Guest guest1, guest2;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webAppCtx;

    @Mock
    private GuestService guestServiceMock;

    @InjectMocks
    private GuestController guestController;

    @Before
    public void setUp() throws Exception {


        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(guestController).build();


//        First fake guest
        guest1 = new Guest();
        guest1.setSecondName("Popov");
        guest1.setFirstName("Gosha");
        guest1.setPatronymic("Dmitrievich");
        guest1.setBirthDay(new GregorianCalendar(1997, 5, 9));
        guest1.setCitizenship("RU");
        guest1.setCountryResidence("RU");
        guest1.setStateResidence("RU");
        guest1.setCityResidence("RU");
        guest1.setStreetResidence("RU");
        guest1.setBuildingResidence("1");
        guest1.setAppartamentResidence("2");
        guest1.setCountryBirth("EU");
        guest1.setStateBirth("Orb obl");
        guest1.setCityBirth("Orb");
        guest1.setPassportData("5690");
        guest1.setPassportDate(new GregorianCalendar(2011, 1, 10));
        guest1.setPassportAuthority("SFMS Central region of Orenburg");
//        End of first guest
    }

    @Test
    public void testAllGuest() throws Exception {
        when(guestServiceMock.findAll()).thenReturn(Arrays.asList(guest1));

        mockMvc.perform(get("/guest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].firstName").value("Gosha"));

        verify(guestServiceMock, times(1)).findAll();
    }

    @Test
    public void testGetGuest() throws Exception {

    }

    @Test
    public void testNewGuest() throws Exception {

    }

    @Test
    public void testUpdateGuest() throws Exception {

    }

    @Test
    public void testDeleteGuest() throws Exception {

    }
}