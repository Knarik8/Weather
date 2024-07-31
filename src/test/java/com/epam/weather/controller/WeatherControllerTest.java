package com.epam.weather.controller;

import com.epam.weather.entity.WeatherData;
import com.epam.weather.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WeatherControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
    }

    @Test
    void testGetWeatherByZipCode() throws Exception {
        WeatherData mockWeatherData = new WeatherData();
        mockWeatherData.setCity("Test City");
        mockWeatherData.setTemperature(25.0);
        mockWeatherData.setWeatherDescription("Sunny");

        when(weatherService.fetchWeatherDataByZipCode("12345")).thenReturn(mockWeatherData);

        mockMvc.perform(get("/weather/by-zip")
                        .param("zipCode", "12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Test City"))
                .andExpect(jsonPath("$.temperature").value(25.0))
                .andExpect(jsonPath("$.weatherDescription").value("Sunny"));
    }

    @Test
    void testGetWeatherByCity() throws Exception {
        WeatherData mockWeatherData = new WeatherData();
        mockWeatherData.setCity("Test City");
        mockWeatherData.setTemperature(25.0);
        mockWeatherData.setWeatherDescription("Sunny");

        when(weatherService.fetchWeatherDataByCity("Test City")).thenReturn(mockWeatherData);

        mockMvc.perform(get("/weather/by-city")
                        .param("city", "Test City"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.city").value("Test City"))
                .andExpect(jsonPath("$.temperature").value(25.0))
                .andExpect(jsonPath("$.weatherDescription").value("Sunny"));
    }
}
