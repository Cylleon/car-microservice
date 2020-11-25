package com.github.cylleon.carmicroservice.models;

import com.github.cylleon.carmicroservice.models.enums.FuelType;
import com.github.cylleon.carmicroservice.models.validators.CorrectCarName;
import com.github.cylleon.carmicroservice.models.validators.CorrectCarPrice;
import com.github.cylleon.carmicroservice.models.validators.CorrectCarYearOfManufacture;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "car")
@Getter
@EqualsAndHashCode
public class Car {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Setter
    @NotNull
    @CorrectCarName
    private String name;
    @Setter
    @NotNull
    private String model;
    @Setter
    @NotNull
    @CorrectCarPrice
    private BigDecimal price;
    @Setter
    @NotNull
    @CorrectCarYearOfManufacture
    private int yearOfManufacture;
    @Setter
    @NotNull
    private FuelType fuelType;
}
