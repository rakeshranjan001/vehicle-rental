package rental.dao;

import rental.exceptions.DuplicateEntryException;
import rental.exceptions.NotFoundException;
import rental.model.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class VehicleDao {
    private static VehicleDao vehicleDao;
    private Map<String, Vehicle> vehicleMap;

    private VehicleDao(){
        this.vehicleMap = new HashMap<>();
    }

    public static VehicleDao getInstance(){
        if(vehicleDao == null){
            vehicleDao = new VehicleDao();
        }
        return vehicleDao;
    }

    public void addVehicle(Vehicle vehicle) throws Exception {
        if(vehicleMap.containsKey(vehicle.getId())){
            throw new DuplicateEntryException("Vehicle already present");
        }

        vehicleMap.put(vehicle.getId(), vehicle);
    }

    public Vehicle getVehicle(String id) throws Exception {
        if(!vehicleMap.containsKey(id)){
            throw new NotFoundException("Vehicle not present !");
        }
        return vehicleMap.get(id);
    }
}
