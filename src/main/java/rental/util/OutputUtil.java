package rental.util;

import lombok.NoArgsConstructor;
import rental.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class OutputUtil {
    public void printListOfVehicles(List<Vehicle> vehicleList){
        List<String> vehicleNameList = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) vehicleNameList.add(vehicle.getId());
        String out = String.join(",",vehicleNameList);
        System.out.println(out);
    }
}
