package rental;

import jdk.jpackage.internal.Log;
import rental.controller.FileInputController;
import rental.dao.BookingDao;
import rental.dao.BranchDao;
import rental.dao.VehicleBookingDao;
import rental.dao.VehicleDao;
import rental.service.*;
import rental.util.BookingUtil;
import rental.util.OutputUtil;

import java.io.File;
import java.util.logging.Logger;

public class Geektrust {
    public static final Logger logger = Logger.getLogger("logs");

    public static void main(String[] args) throws Exception {
        File inputFile = new File(args[0]);
        VehicleService vehicleService = new VehicleServiceImpl(VehicleDao.getInstance());
        BranchDao branchDao = BranchDao.getInstance();
        BookingDao bookingDao = BookingDao.getInstance();
        InventoryManagerService inventoryManagerService = new InventoryManagerService(vehicleService,new BranchServiceImpl(branchDao));
        BookingService bookingService = new BookingService(bookingDao, VehicleBookingDao.getInstance(),new BookingUtil(),vehicleService,inventoryManagerService);
        BranchService branchService = new BranchServiceImpl(branchDao);
        FileInputController fileInputController = new FileInputController(inputFile, branchService, inventoryManagerService,
                bookingService, new OutputUtil());
        fileInputController.process();
    }
}
