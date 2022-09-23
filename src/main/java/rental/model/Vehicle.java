package rental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private String id;
    private String branchId;
    private String type;
    private Integer price;

    public Vehicle(String id, String type, Integer price) {
        this.id = id;
        this.type = type;
        this.price = price;
    }
}
