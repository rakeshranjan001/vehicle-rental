package rental.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleBookingDao {
    private static VehicleBookingDao vehicleBookingDao;

    private Map<String, List<Integer>> vehicleBookingIdMap;

    private VehicleBookingDao(){
        vehicleBookingIdMap = new HashMap<>();
    }

    public static VehicleBookingDao getInstance(){
        if(vehicleBookingDao == null){
            vehicleBookingDao = new VehicleBookingDao();
        }
        return vehicleBookingDao;
    }

    public List<Integer> getBookingIdsForVehicle(String vehicleId){
        List<Integer> bookingIdList = new ArrayList<>();
        bookingIdList = vehicleBookingIdMap.get(vehicleId);
        return bookingIdList;
    }

    public void addBookingForVehicle(String vehicleId, Integer bookingId){
        if(!vehicleBookingIdMap.containsKey(vehicleId)){
            vehicleBookingIdMap.put(vehicleId,new ArrayList<>());
        }
        vehicleBookingIdMap.get(vehicleId).add(bookingId);
    }
}
