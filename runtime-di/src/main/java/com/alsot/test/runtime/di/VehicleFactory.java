package com.alsot.test.runtime.di;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alsot.test.runtime.di.vehicle.Vehicle;

@Component
public class VehicleFactory {

    @Autowired
    private List<Vehicle> vehicles;
    
    public Vehicle getVehicle(VehicleType vehicleType) {
        Vehicle vehicle = null;
        for (Vehicle vec : vehicles) {
            VehicleQualifier vq = vec.getClass().getAnnotation(VehicleQualifier.class);
            if (vq != null && vq.type() == vehicleType) {
                vehicle = vec;
                break;
            }
        }
        return vehicle;
    }
}
