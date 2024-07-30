package com.epam.weather.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherUpdateScheduler {

    private final WeatherService weatherService;

    public WeatherUpdateScheduler(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Scheduled(fixedDelay = 3600000) // Update every hour
    public void updateWeatherData() {
        String[] cities = {"New York", "Los Angeles", "Chicago"}; // Example cities

        for (String city : cities) {
            weatherService.fetchWeatherDataByCity(city);
        }
    }
}
