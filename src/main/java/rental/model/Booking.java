package rental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Booking {
    private Integer id;
    private String branchId;
    private String vehicleId;
    private Integer startTime;
    private Integer endTime;
    private Integer amount;
}
