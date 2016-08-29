package com.alsot.test.runtime.di.vehicle;

import org.springframework.stereotype.Component;

@Component
//@VehicleQualifier(type = VehicleType.SPACE_SHIP)
public class SpaceShip implements Vehicle {

    @Override
    public void drive() {
        System.out.println("moon sailing...");
    }

}
