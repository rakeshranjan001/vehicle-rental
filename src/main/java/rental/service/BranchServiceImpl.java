package rental.service;

import rental.dao.BranchDao;
import rental.model.Branch;

import java.util.List;

public class BranchServiceImpl implements BranchService{

    BranchDao branchDao;

    public BranchServiceImpl(){
        branchDao = BranchDao.getInstance();
    }

    @Override
    public void addBranch(String branchName, List<String> vehicleTypes) throws Exception {
        branchDao.addBranch(new Branch(branchName,vehicleTypes));
    }

    @Override
    public Branch getBranch(String branchId) {
        return branchDao.getBranch(branchId);
    }

    @Override
    public void addVehicle(String branchName, String vehicleId) {
        Branch branch = branchDao.getBranch(branchName);
        branch.addVehicle(vehicleId);
    }
}
