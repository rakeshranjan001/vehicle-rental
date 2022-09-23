package rental.service;

import rental.model.Vehicle;

public interface VehicleService {
    void addVehicle(String branchName, String vehicleId, String vehicleType, Integer price) throws Exception;

    Vehicle getVehicle(String id) throws Exception;
}
