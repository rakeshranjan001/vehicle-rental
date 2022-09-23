package rental.service;

import rental.model.Branch;
import rental.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManagerService {

    private final VehicleService vehicleService;
    private final BranchService branchService;
    public InventoryManagerService(){
        vehicleService = new VehicleServiceImpl();
        branchService = new BranchServiceImpl();
    }

    /*
     Branch Name, Vehicle Type, vehicle id, price
    */
    public void addVehicleToBranch(String branchId, String vehicleType, String vehicleId, String price) throws Exception {

        if(!branchService.getBranch(branchId).getTypes().contains(vehicleType)){
            throw new Exception("Vehicle Type Not Supported");
        }

        vehicleService.addVehicle(branchId,vehicleId,vehicleType,Integer.parseInt(price));
        branchService.addVehicle(branchId,vehicleId);
    }

    public List<Vehicle> getAllVehiclesFromBranch(String branchId){
        Branch branch = branchService.getBranch(branchId);
        List<Vehicle> vehicleList = new ArrayList<>();

        branch.getVehicleIds().stream().forEach(id -> {
            try {
                vehicleList.add(vehicleService.getVehicle(id));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return vehicleList;
    }

    public List<Vehicle> getAllVehiclesFromBranch(String branchId, String vehicleType){
        return getAllVehiclesFromBranch(branchId).stream()
                .filter(vehicle -> vehicle.getType().equals(vehicleType))
                .collect(Collectors.toList());
    }
}
