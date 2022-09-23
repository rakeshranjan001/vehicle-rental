package rental.service;

import rental.dao.BookingDao;
import rental.dao.VehicleBookingDao;
import rental.dao.VehicleDao;
import rental.model.Booking;
import rental.model.Vehicle;
import rental.util.BookingUtil;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookingService {
    private final BookingDao bookingDao;
    private final VehicleBookingDao vehicleBookingDao;
    private final BookingUtil bookingUtil;
    private final VehicleService vehicleService;

    private final InventoryManagerService inventoryManagerService;

    public BookingService(){
        bookingDao = BookingDao.getInstance();
        vehicleBookingDao = VehicleBookingDao.getInstance();
        inventoryManagerService = new InventoryManagerService();
        vehicleService = new VehicleServiceImpl();
        bookingUtil = new BookingUtil();
    }


    private List<Integer> getBookingIdsForVehicles(List<Vehicle> vehicleList){
        return vehicleList.stream()
                .map(vehicle -> vehicleBookingDao.getBookingIdsForVehicle(vehicle.getId())).filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public List<Vehicle> displayAllVehiclesForBranch(String branchId, String startTime, String endTime){
        List<Vehicle> vehicleList = inventoryManagerService.getAllVehiclesFromBranch(branchId);
        vehicleList.sort(Comparator.comparingDouble(Vehicle::getPrice));
        List<Integer> bookingIdList = getBookingIdsForVehicles(vehicleList);

        if(bookingIdList.size() == 0)   return vehicleList;

        List<Booking> bookingList = bookingIdList.stream().map(id->bookingDao.getBooking(id)).collect(Collectors.toList());
        List<String> vehicleIdList = bookingUtil.filterAvailableVehiclesWithTime(
                vehicleList, bookingList,
                Integer.parseInt(startTime), Integer.parseInt(endTime));
        List<Vehicle> finalList = vehicleIdList.stream().map(id-> {
            try {
                return vehicleService.getVehicle(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        finalList.sort(Comparator.comparingDouble(Vehicle::getPrice));

        return finalList;
    }

    public Integer createBooking(String branchId, String vehicleType, String startTime, String endTime) throws Exception {
        List<Vehicle> vehicleListOfType = inventoryManagerService.getAllVehiclesFromBranch(branchId,vehicleType);
        List<Integer> listOfBookingIds = getBookingIdsForVehicles(vehicleListOfType);

        Vehicle vehicle;
        if(listOfBookingIds.size() == 0){
            if(vehicleListOfType.size() == 0){
                throw new Exception("No vehicles available");
            }
            vehicle = vehicleListOfType.get(0);
        }else{
            List<Booking> bookingList = listOfBookingIds.stream().map(id->bookingDao.getBooking(id)).collect(Collectors.toList());
            List<String> vehicleIdList = bookingUtil.filterAvailableVehiclesWithTime(
                    vehicleListOfType, bookingList,
                    Integer.parseInt(startTime), Integer.parseInt(endTime));
            if(vehicleIdList.size() == 0){
                throw new Exception("No vehicles available");
            }
            vehicle = vehicleService.getVehicle(vehicleIdList.get(0));
        }

        Booking booking = Booking.builder()
                .id(bookingDao.getAvailableId())
                .branchId(branchId)
                .vehicleId(vehicle.getId())
                .startTime(Integer.parseInt(startTime))
                .endTime(Integer.parseInt(endTime))
                .build();
        booking.setAmount(bookingUtil.priceCalculator(vehicle,booking));

        bookingDao.addBooking(booking);
        vehicleBookingDao.addBookingForVehicle(vehicle.getId(),booking.getId());

        return booking.getAmount();
    }
}
