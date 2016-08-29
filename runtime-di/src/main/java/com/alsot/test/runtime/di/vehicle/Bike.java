package com.alsot.test.runtime.di.vehicle;

import org.springframework.stereotype.Component;

import com.alsot.test.runtime.di.VehicleQualifier;
import com.alsot.test.runtime.di.VehicleType;

@Component
@VehicleQualifier(type = VehicleType.BIKE)
public class Bike implements Vehicle {

    public void drive() {
        System.out.println("cycling...");
    }

}
