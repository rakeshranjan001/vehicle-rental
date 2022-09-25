package rental;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import rental.dao.BranchDao;
import rental.dao.VehicleDao;
import rental.model.Vehicle;
import rental.service.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InventoryManagerTest {
    VehicleDao vehicleDao = VehicleDao.getInstance();
    VehicleService vehicleService = new VehicleServiceImpl(vehicleDao);
    BranchDao branchDao = BranchDao.getInstance();
    BranchService branchService = new BranchServiceImpl(branchDao);
    InventoryManagerService inventoryManagerService = new InventoryManagerService(vehicleService,branchService);

    @BeforeAll
    public void addBranch() throws Exception {
        List<String> vehicleTypeList = new ArrayList<>();
        vehicleTypeList.add("CAR");
        vehicleTypeList.add("BIKE");
        branchService.addBranch("T1",vehicleTypeList);

        List<String> vehicleTypeList2 = new ArrayList<>();
        vehicleTypeList2.add("SCOOTER");
        vehicleTypeList2.add("VAN");
        branchService.addBranch("T2",vehicleTypeList2);
    }

    @Test
    public void addVehicleTest() throws Exception {
        inventoryManagerService.addVehicleToBranch("T1","CAR","Test-01","999");

        List<Vehicle> response = inventoryManagerService.getAllVehiclesFromBranch("T1");

        assertEquals(response.size(),1);
        assertEquals(response.get(0).getPrice(),new Integer(999));
        assertNotNull(vehicleService.getVehicle("Test-01"));
        assertEquals(inventoryManagerService.getAllVehiclesFromBranch("T3").size(),0);
    }

    @Test
    public void getVehiclesTest() throws Exception {
        inventoryManagerService.addVehicleToBranch("T2","VAN","V1","786");
        inventoryManagerService.addVehicleToBranch("T2","SCOOTER","V2","780");

        List<Vehicle> vehicleList = inventoryManagerService.getAllVehiclesFromBranch("T2");
        assertEquals(vehicleList.size(),2);
        assertTrue(vehicleList.contains(new Vehicle("V1","T2","VAN",786)));
        assertTrue(vehicleList.contains(new Vehicle("V2","T2","SCOOTER",780)));
    }
}
