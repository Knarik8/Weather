package com.epam.weather.service;

import com.epam.weather.dto.WeatherApiResponse;
import com.epam.weather.entity.WeatherData;
import com.epam.weather.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    private final String weatherApiUrl = "https://api.weatherapi.com/v1/current.json";
    private final WeatherDataRepository weatherDataRepository;

    public WeatherService(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    public WeatherData fetchWeatherDataByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = weatherApiUrl + "?key=" + apiKey + "&q=" + city;
        WeatherApiResponse response = restTemplate.getForObject(apiUrl, WeatherApiResponse.class);

        if (response != null) {
            WeatherData weatherData = new WeatherData();
            weatherData.setCity(city);
            weatherData.setWeatherDescription(response.getCurrent().getCondition().getText());
            weatherData.setTemperature(response.getCurrent().getTempC());
            weatherData.setLastUpdated(LocalDateTime.now());

            return weatherDataRepository.save(weatherData);
        } else {
            return null;
        }
    }

    public WeatherData fetchWeatherDataByZipCode(String zipCode) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrl = weatherApiUrl + "?key=" + apiKey + "&q=" + zipCode;
        WeatherApiResponse response = restTemplate.getForObject(apiUrl, WeatherApiResponse.class);

        if (response != null) {
            WeatherData weatherData = new WeatherData();
            weatherData.setCity(response.getLocation().getName());
            weatherData.setWeatherDescription(response.getCurrent().getCondition().getText());
            weatherData.setTemperature(response.getCurrent().getTempC());
            weatherData.setLastUpdated(LocalDateTime.now());

            return weatherDataRepository.save(weatherData);
        } else {
            return null;
        }
    }
}
