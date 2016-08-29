package com.alsot.test.runtime.di.vehicle;

import org.springframework.stereotype.Component;

import com.alsot.test.runtime.di.VehicleQualifier;
import com.alsot.test.runtime.di.VehicleType;

@Component
@VehicleQualifier(type = VehicleType.CAR)
public class Car implements Vehicle {

    public void drive() {
        System.out.println("driving...");
    }

}
