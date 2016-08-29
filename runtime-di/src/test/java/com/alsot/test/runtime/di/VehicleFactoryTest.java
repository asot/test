package com.alsot.test.runtime.di;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alsot.test.runtime.di.vehicle.Bike;
import com.alsot.test.runtime.di.vehicle.Boat;
import com.alsot.test.runtime.di.vehicle.Car;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {VehicleFactoryTestConfig.class})
public class VehicleFactoryTest {

    @Autowired
    private VehicleFactory factory;
    
    @Test
    public void testFactory() {
        assertTrue("bike service returned", factory.getVehicle(VehicleType.BIKE) instanceof Bike);
        assertTrue("boat service returned", factory.getVehicle(VehicleType.BOAT) instanceof Boat);
        assertTrue("car service returned", factory.getVehicle(VehicleType.CAR) instanceof Car);
        assertNull("space ship service not available", factory.getVehicle(VehicleType.SPACE_SHIP));
    }
}
