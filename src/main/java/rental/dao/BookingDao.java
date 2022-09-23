package rental.dao;

import rental.model.Booking;

import java.util.HashMap;
import java.util.Map;

public class BookingDao {
    private static BookingDao bookingDao;
    private static Integer id;
    private Map<Integer, Booking> bookingMap;
    private BookingDao(){
        bookingMap = new HashMap<>();
        id = 0;
    }

    public static BookingDao getInstance(){
        if(bookingDao == null){
            bookingDao = new BookingDao();
        }
        return bookingDao;
    }

    public void addBooking(Booking booking){

        bookingMap.put(booking.getId(), booking);
    }

    public Booking getBooking(Integer id){
        return bookingMap.get(id);
    }

    public Integer getAvailableId(){
        return id++;
    }
}
