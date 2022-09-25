package rental.service;

import rental.model.Branch;

import java.util.List;

public interface BranchService {

    void addBranch(String branchName, List<String> vehicleTypes) throws Exception;

    Branch getBranch(String branchId);

    void addVehicle(String branchName, String vehicleId);

    void removeBranch(String branchName);
}
