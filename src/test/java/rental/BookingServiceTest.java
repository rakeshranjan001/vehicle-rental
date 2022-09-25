package rental;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import rental.dao.BookingDao;
import rental.dao.BranchDao;
import rental.dao.VehicleBookingDao;
import rental.dao.VehicleDao;
import rental.exceptions.NotFoundException;
import rental.model.Vehicle;
import rental.service.*;
import rental.util.BookingUtil;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookingServiceTest {
    BookingDao bookingDao = BookingDao.getInstance();
    BranchService branchService = new BranchServiceImpl(BranchDao.getInstance());
    VehicleDao vehicleDao = VehicleDao.getInstance();
    VehicleBookingDao vehicleBookingDao = VehicleBookingDao.getInstance();
    VehicleService vehicleService = new VehicleServiceImpl(vehicleDao);

    BookingUtil bookingUtil = new BookingUtil();

    InventoryManagerService inventoryManagerService = new InventoryManagerService(vehicleService,branchService);

    BookingService bookingService = new BookingService(bookingDao,vehicleBookingDao,bookingUtil,vehicleService,inventoryManagerService);


    @BeforeAll
    public void addVehicle() throws Exception {
        List<String> vehicleTypeList = new ArrayList<>();
        vehicleTypeList.add("CAR");
        vehicleTypeList.add("BIKE");
        branchService.addBranch("TB1",vehicleTypeList);
        inventoryManagerService.addVehicleToBranch("TB1","CAR","test-1","200");
        inventoryManagerService.addVehicleToBranch("TB1","BIKE","test-2","100");
    }

    @Test
    public void displayVehiclesForBranch(){
        List<Vehicle> vehicleList = bookingService.displayAllVehiclesForBranch("TB1","1","10");
        assertEquals(vehicleList.size(),2);
        assertEquals(vehicleList.get(0).getPrice(),new Integer(100));
    }

    @Test
    public void successfulBookingTest() throws Exception {
        assertEquals(bookingService.createBooking("TB1","BIKE","1","7"),new Integer(600));
    }

    @Test()
    public void failedBookingTest() throws Exception {
        NotFoundException thrown = assertThrows(
                NotFoundException.class,
                ()->bookingService.createBooking("TB1","VAN","1","5"),
                "No Exception"
        );

        assertTrue(thrown.getMessage().contains("No vehicles available"));
    }
}
