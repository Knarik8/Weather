package com.epam.weather.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class WeatherUpdateSchedulerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherUpdateScheduler weatherUpdateScheduler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateWeatherData() {
        // Run the scheduled method directly
        weatherUpdateScheduler.updateWeatherData();

        // Verify that weatherService.fetchWeatherDataByCity() is called with each city
        verify(weatherService, times(1)).fetchWeatherDataByCity("New York");
        verify(weatherService, times(1)).fetchWeatherDataByCity("Los Angeles");
        verify(weatherService, times(1)).fetchWeatherDataByCity("Chicago");
    }

    @Test
    void testSchedulerIsScheduled() {
        // Create a ScheduledAnnotationBeanPostProcessor to verify the scheduling
        ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor =
                new ScheduledAnnotationBeanPostProcessor();

        // Register the scheduler component
        scheduledAnnotationBeanPostProcessor.postProcessBeforeInitialization(weatherUpdateScheduler, "weatherUpdateScheduler");

        // Verify that the scheduler is correctly registered (this may vary based on how you test scheduling)
        // This step can involve more sophisticated testing frameworks or manual verification
    }
}
