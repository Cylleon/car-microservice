package com.github.cylleon.carmicroservice.controllers;

import com.github.cylleon.carmicroservice.models.Car;
import com.github.cylleon.carmicroservice.models.enums.FuelType;
import com.github.cylleon.carmicroservice.repositories.CarRepository;
import com.github.cylleon.carmicroservice.utils.CarModelAssembler;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    CarRepository carRepository;
    CarController carController;

    @BeforeEach
    public void setup() {
        carRepository = Mockito.mock(CarRepository.class);
        CarModelAssembler carModelAssembler = new CarModelAssembler();
        carController = new CarController(carRepository, carModelAssembler);
    }

    @Test
    void findAll() {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Car car = new Car();
            car.setId((long) i);
            car.setName("CarName");
            car.setModel("Car");
            car.setPrice(BigDecimal.valueOf(10000 * i));
            car.setYearOfManufacture(2006 + i);
            car.setFuelType(FuelType.DIESEL);
            cars.add(car);
        }
        Mockito.when(carRepository.findAll()).thenReturn(cars);

        List<Car> result = new ArrayList<>();
        for (EntityModel<Car> carEntityModel : carController.findAll()) {
            result.add(carEntityModel.getContent());
        }

        Mockito.verify(carRepository, Mockito.atMostOnce()).findAll();
        Assert.assertEquals(cars, result);
    }

    @Test
    void findOne() {
        Long id = 10L;
        Car car = new Car();
        car.setId(id);
        car.setName("CarName");
        car.setModel("Car");
        car.setPrice(BigDecimal.valueOf(10000));
        car.setYearOfManufacture(2014);
        car.setFuelType(FuelType.PETROL);

        Mockito.when(carRepository.findById(Mockito.eq(id))).thenReturn(Optional.of(car));

        EntityModel<Car> result = carController.findOne(id);

        Mockito.verify(carRepository, Mockito.atMostOnce()).findById(Mockito.eq(id));
        Assert.assertEquals(car, result.getContent());
    }

    @Test
    void postCar() {
        Car car = new Car();
        car.setId(5L);
        car.setName("CarName");
        car.setModel("Car");
        car.setPrice(BigDecimal.valueOf(10000));
        car.setYearOfManufacture(2014);
        car.setFuelType(FuelType.PETROL);

        Mockito.when(carRepository.save(Mockito.eq(car))).thenReturn(car);

        ResponseEntity<EntityModel<Car>> result = carController.postCar(car);

        Mockito.verify(carRepository, Mockito.atMostOnce()).save(Mockito.eq(car));

        Assert.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assert.assertEquals(car, result.getBody().getContent());
    }

    @Test
    void deleteCar() {
        Long id = 7L;

        ResponseEntity<EntityModel<Car>> result = carController.deleteCar(id);

        Mockito.verify(carRepository, Mockito.atMostOnce()).deleteById(Mockito.eq(id));

        Assert.assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Assert.assertNull(result.getBody());
    }
}