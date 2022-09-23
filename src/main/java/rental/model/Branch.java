package rental.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Branch {
    private String id;
    private Set<String> types;
    private List<String> vehicleIds;

    public Branch(String id, List<String> types) {
        this.id = id;
        this.vehicleIds = new ArrayList<>();
        this.types = types.stream().collect(Collectors.toSet());
    }

    public void addVehicle(String vehicleId){
        vehicleIds.add(vehicleId);
    }
}
