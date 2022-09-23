package rental.service;

import rental.dao.VehicleDao;
import rental.model.Vehicle;

public class VehicleServiceImpl implements VehicleService{

    private final VehicleDao vehicleDao;

    public VehicleServiceImpl(){
        this.vehicleDao = VehicleDao.getInstance();
    }

    @Override
    public void addVehicle(String branchName, String vehicleId, String vehicleType, Integer price) throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .branchId(branchName)
                .id(vehicleId)
                .type(vehicleType)
                .price(price)
                .build();
        vehicleDao.addVehicle(vehicle);
    }

    @Override
    public Vehicle getVehicle(String id) throws Exception {
        return vehicleDao.getVehicle(id);
    }
}
