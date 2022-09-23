package rental.util;

import rental.dao.BookingDao;
import rental.model.Booking;
import rental.model.Vehicle;
import rental.service.BookingService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingUtil {
    public List<String> filterAvailableVehiclesWithTime(List<Vehicle> vehicleList, List<Booking> bookingList, int startTime, int endTime){
       // List<Vehicle> vehicleList = new ArrayList<>();
        Set<String> availableVehicleSet = vehicleList.stream().map(vehicle -> vehicle.getId()).collect(Collectors.toSet());

        bookingList.forEach(booking -> {
            if(booking.getStartTime() >= startTime && booking.getStartTime() <= endTime){
                availableVehicleSet.remove(booking.getVehicleId());
            } else if (booking.getEndTime() >= startTime && booking.getEndTime() <= endTime) {
                availableVehicleSet.remove(booking.getVehicleId());
            }
        });

        return  availableVehicleSet.stream().collect(Collectors.toList());
    }

    public Integer priceCalculator(Vehicle vehicle, Booking booking){
        return vehicle.getPrice() * (booking.getEndTime() - booking.getStartTime());
    }
}
