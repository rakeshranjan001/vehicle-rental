package rental.service;

import rental.util.OutputUtil;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileInputProcessor {

    private final File inputFile;
    private final BranchService branchService;
    private final InventoryManagerService inventoryManagerService;
    private final BookingService bookingService;
    private final OutputUtil outputUtil;

    public FileInputProcessor(File inputFile) {
        this.inputFile = inputFile;
        this.branchService = new BranchServiceImpl();
        this.inventoryManagerService = new InventoryManagerService();
        this.bookingService = new BookingService();
        this.outputUtil = new OutputUtil();
    }

    public void process() throws Exception {
        Scanner inputFile = new Scanner(this.inputFile);

        while (inputFile.hasNextLine()) {
            String line = inputFile.nextLine();
            String[] parts = line.split(" ");

            switch (parts[0]){
                case "ADD_BRANCH":
                    try {
                        branchService.addBranch(parts[1], Arrays.asList(parts[2].split(",")));
                        System.out.println("TRUE");
                    }catch (Exception e){
                        System.out.println("FALSE");
                    }
                    break;
                case "ADD_VEHICLE" :
                    /*
                     (Branch Name, Vehicle Type, vehicle id, price)
                    */
                    try {
                        inventoryManagerService.addVehicleToBranch(parts[1],parts[2],parts[3],parts[4]);
                        System.out.println("TRUE");
                    }catch (Exception e){
                        System.out.println("FALSE");
                    }

                    break;
                case "BOOK" :
                    try{
                        System.out.println(bookingService.createBooking(parts[1], parts[2], parts[3], parts[4]));
                    }catch (Exception e){
                        System.out.println("-1");
                    }
                    break;
                case "DISPLAY_VEHICLES":
                    outputUtil.printListOfVehicles(bookingService.displayAllVehiclesForBranch(parts[1],parts[2],parts[3]));
                    break;
                default: System.out.println(parts[0] + " : Action Not supported !!");
            }
        }
    }
}
