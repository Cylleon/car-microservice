package com.github.cylleon.carmicroservice.repositories;

import com.github.cylleon.carmicroservice.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
