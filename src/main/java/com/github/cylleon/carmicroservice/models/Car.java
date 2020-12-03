package com.github.cylleon.carmicroservice.models;

import com.github.cylleon.carmicroservice.models.enums.FuelType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Entity(name = "car")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Car {
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Setter
    @NotNull
    @Pattern(regexp = "^[_A-z0-9]*$", message = "Incorrect name")
    private String name;
    @Setter
    @NotNull
    private String model;
    @Setter
    @NotNull
    @DecimalMin(value = "1", message = "Incorrect price")
    private BigDecimal price;
    @Setter
    @NotNull
    @DecimalMin(value = "2005", message = "Incorrect year of manufacture")
    private int yearOfManufacture;
    @Setter
    @NotNull
    private FuelType fuelType;
}
