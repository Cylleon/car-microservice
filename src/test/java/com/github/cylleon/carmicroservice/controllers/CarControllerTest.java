package com.github.cylleon.carmicroservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cylleon.carmicroservice.models.Car;
import com.github.cylleon.carmicroservice.models.enums.FuelType;
import com.github.cylleon.carmicroservice.repositories.CarRepository;
import com.github.cylleon.carmicroservice.utils.CarModelAssembler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(CarController.class)
@ContextConfiguration(classes = {CarModelAssembler.class})
@WebMvcTest(controllers = CarController.class)
class CarControllerTest {

    @MockBean
    private CarRepository carRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findAll() throws Exception {
        List<Car> cars = List.of(
                Car.builder().build(),
                Car.builder().build(),
                Car.builder().build(),
                Car.builder().build(),
                Car.builder().build()
        );
        Mockito.when(carRepository.findAll()).thenReturn(cars);

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.carList", hasSize(5)));
    }

    @Test
    void findOne() throws Exception {
        Long id = 10L;
        Car car = createCar(id, "CarName", BigDecimal.valueOf(10000), 2014);

        Mockito.when(carRepository.findById(Mockito.eq(id))).thenReturn(Optional.of(car));

        mockMvc.perform(get("/cars/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", hasToString(String.valueOf(id))));
    }

    @Test
    void postCar() throws Exception {
        Long id = 5L;
        Car car = createCar(id, "CarName", BigDecimal.valueOf(10000), 2014);

        Mockito.when(carRepository.save(Mockito.eq(car))).thenReturn(car);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(car)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", hasToString(String.valueOf(id))));

        Mockito.verify(carRepository, Mockito.atMostOnce()).save(Mockito.eq(car));
    }

    @Test
    void deleteCar() throws Exception {
        Long id = 7L;

        mockMvc.perform(delete("/cars/" + id))
                .andExpect(status().isNoContent());

        Mockito.verify(carRepository, Mockito.atMostOnce()).deleteById(Mockito.eq(id));
    }

    private Car createCar(Long id, String carName, BigDecimal carPrice, int yearOfManufacture) {
        return Car.builder()
                .id(id)
                .name(carName)
                .price(carPrice)
                .yearOfManufacture(yearOfManufacture)
                .model("Car")
                .fuelType(FuelType.PETROL)
                .build();
    }
}