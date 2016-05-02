package com.popovgosha.motelbackend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.popovgosha.motelbackend.config.WebConfig;
import com.popovgosha.motelbackend.domain.Guest;
import com.popovgosha.motelbackend.services.GuestService;
import com.popovgosha.motelbackend.utils.DateConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

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

    private DateConverter dateConverter;
    private Guest guest1, guest2, guest3;

    private MockMvc mockMvc;


    @Mock
    private GuestService guestServiceMock;

    @InjectMocks
    private GuestController guestController;

    @Before
    public void setUp() throws Exception {
        dateConverter = new DateConverter();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(guestController).build();


//        First fake guest
        guest1 = new Guest();
        guest1.setFullName("Popov Gosha Dmitrievich");
        guest1.setBirthDay(dateConverter.calendarToDate(new GregorianCalendar(1997, 5, 9)));
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
        guest1.setPassportDate(dateConverter.calendarToDate(new GregorianCalendar(2011, 1, 10)));
        guest1.setPassportAuthority("SFMS Central region of Orenburg");

        guest2 = new Guest();
        guest2.setFullName("P Gosha Dmitrievich");
        guest2.setBirthDay(dateConverter.calendarToDate(new GregorianCalendar(1997, 5, 9)));
        guest2.setCitizenship("RU");
        guest2.setCountryResidence("RU");
        guest2.setStateResidence("RU");
        guest2.setCityResidence("RU");
        guest2.setStreetResidence("RU");
        guest2.setBuildingResidence("1");
        guest2.setAppartamentResidence("2");
        guest2.setCountryBirth("EU");
        guest2.setStateBirth("Orb obl");
        guest2.setCityBirth("Orb");
        guest2.setPassportData("5691");
        guest2.setPassportDate(dateConverter.calendarToDate(new GregorianCalendar(2011, 1, 10)));
        guest2.setPassportAuthority("SFMS Central region of Orenburg");

        guest3 = null;
    }

    @Test
    public void testAllGuest() throws Exception {
        when(guestServiceMock.findAll()).thenReturn(Arrays.asList(guest1, guest2));

        mockMvc.perform(get("/guest").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].passportData").value("5690"))
                .andExpect(jsonPath("$[1].passportData").value("5691"));

        when(guestServiceMock.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/guest").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

        verify(guestServiceMock, times(2)).findAll();
    }

    @Test
    public void testGetGuest() throws Exception {

        when(guestServiceMock.findOne(1L)).thenReturn(guest1);
        when(guestServiceMock.findOne(2L)).thenReturn(null);

        mockMvc.perform(get("/guest/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("citizenship").value("RU"));

        mockMvc.perform(get("/guest/2").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());

        verify(guestServiceMock, times(2)).findOne(anyLong());
    }

    @Test
    public void testNewGuest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String guest1JSON = mapper.writeValueAsString(guest1);
        String guest2JSON = mapper.writeValueAsString(guest2);
        String guest3JSON = mapper.writeValueAsString(guest3);
        String guest4JSON;

        when(guestServiceMock.save(guest1)).thenReturn(guest1);

//        Valid Guest1

        when(guestServiceMock.isFreePassportSeriesNumber("5690")).thenReturn(true);

        mockMvc.perform(post("/guest").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(guest1JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("citizenship").value("RU"));

//        Guest2 with conflict
        when(guestServiceMock.isFreePassportSeriesNumber("5691")).thenReturn(false);

        mockMvc.perform(post("/guest").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(guest2JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isConflict());

//          Post with Guest3 null-object
        mockMvc.perform(post("/guest").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(guest3JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());


        verify(guestServiceMock, times(1)).save(anyObject());

    }

    @Test
    public void testUpdateGuest() throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        String guest1JSON = mapper.writeValueAsString(guest1);
        String guest2JSON = mapper.writeValueAsString(guest2);
        String guest3JSON = mapper.writeValueAsString(null);

        when(guestServiceMock.save(guest1)).thenReturn(guest1);
        when(guestServiceMock.findOne(1L)).thenReturn(guest1);
        when(guestServiceMock.findOne(3L)).thenReturn(null);
        when(guestServiceMock.isFreePassportSeriesNumber("5690")).thenReturn(true);
        when(guestServiceMock.isFreePassportSeriesNumber("5691")).thenReturn(false);

//        All okey
        mockMvc.perform(put("/guest/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(guest1JSON)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("citizenship").value("RU"));

//        Not free passport
        mockMvc.perform(put("/guest/2").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(guest2JSON))
                .andExpect(status().isNotFound());

//        Not Found
        mockMvc.perform(put("/guest/3").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(guest2JSON))
                .andExpect(status().isNotFound());

//       Null object
        mockMvc.perform(put("/guest/1").contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(guest3JSON))
                .andExpect(status().isBadRequest());


        verify(guestServiceMock, times(1)).save(anyObject());
    }

    @Test
    public void testDeleteGuest() throws Exception {

        when(guestServiceMock.findOne(1L)).thenReturn(guest1);

        mockMvc.perform(delete("/guest/1"))
                .andExpect(status().isOk());

        when(guestServiceMock.findOne(2L)).thenReturn(null);

        mockMvc.perform(delete("/guest/2"))
                .andExpect(status().isNotFound());

        verify(guestServiceMock, times(1)).delete(anyLong());
    }

    @Test
    public void testSearchByPassportData() throws Exception {

        when(guestServiceMock.searchByPassportData(anyString())).thenReturn(Arrays.asList(guest1,guest2));

        mockMvc.perform(get("/guest/search").param("passportData", "str").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].passportData").value("5690"))
                .andExpect(jsonPath("$[1].passportData").value("5691"));

    }

}