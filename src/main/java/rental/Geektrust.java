package rental;

import rental.dao.BookingDao;
import rental.dao.VehicleBookingDao;
import rental.dao.VehicleDao;
import rental.service.*;
import rental.util.BookingUtil;
import rental.util.OutputUtil;

import java.io.File;

public class Geektrust {
    public static void main(String[] args) throws Exception {
        File inputFile = new File(args[0]);
        VehicleService vehicleService = new VehicleServiceImpl(VehicleDao.getInstance());
        InventoryManagerService inventoryManagerService = new InventoryManagerService(vehicleService,new BranchServiceImpl());
        BookingService bookingService = new BookingService(BookingDao.getInstance(), VehicleBookingDao.getInstance(),new BookingUtil(),vehicleService,inventoryManagerService);
        BranchService branchService = new BranchServiceImpl();

        FileInputProcessor fileInputProcessor = new FileInputProcessor(inputFile, branchService, inventoryManagerService,
                bookingService, new OutputUtil());
        fileInputProcessor.process();
    }
}
