package com.epam.weather.controller;

import com.epam.weather.entity.WeatherData;
import com.epam.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/by-zip")
    public WeatherData getWeatherByZipCode(@RequestParam String zipCode) {
        return weatherService.fetchWeatherDataByZipCode(zipCode);
    }

    @GetMapping("/by-city")
    public WeatherData getWeatherByCity(@RequestParam String city) {
        return weatherService.fetchWeatherDataByCity(city);
    }
}
