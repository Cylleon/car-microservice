package com.github.cylleon.carmicroservice.controllers;

import com.github.cylleon.carmicroservice.models.Car;
import com.github.cylleon.carmicroservice.repositories.CarRepository;
import com.github.cylleon.carmicroservice.utils.CarModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CarController {

    private final CarRepository carRepository;

    private final CarModelAssembler carModelAssembler;

    @Autowired
    public CarController(CarRepository carRepository, CarModelAssembler carModelAssembler) {
        this.carRepository = carRepository;
        this.carModelAssembler = carModelAssembler;
    }

    @GetMapping("/cars")
    public CollectionModel<EntityModel<Car>> findAll() {
        List<EntityModel<Car>> cars = carRepository.findAll().stream()
                .map(carModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cars,
                linkTo(methodOn(CarController.class).findAll()).withSelfRel());
    }

    @GetMapping("/cars/{id}")
    public EntityModel<Car> findOne(@PathVariable("id") Long id) {
        Car car = carRepository.findById(id).orElseThrow();

        return carModelAssembler.toModel(car);
    }

    @PostMapping("/cars")
    public ResponseEntity<EntityModel<Car>> postCar(@Valid @RequestBody Car car) {
        car = carRepository.save(car);
        EntityModel<Car> newCar = carModelAssembler.toModel(car);
        return ResponseEntity.created(newCar.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(newCar);
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<EntityModel<Car>> deleteCar(@PathVariable Long id) {
        carRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
