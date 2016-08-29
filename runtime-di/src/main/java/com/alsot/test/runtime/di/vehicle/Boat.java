package com.alsot.test.runtime.di.vehicle;

import org.springframework.stereotype.Component;

import com.alsot.test.runtime.di.VehicleQualifier;
import com.alsot.test.runtime.di.VehicleType;

@Component
@VehicleQualifier(type = VehicleType.BOAT)
public class Boat implements Vehicle {

    public void drive() {
        System.out.println("sailing...");
    }

}
