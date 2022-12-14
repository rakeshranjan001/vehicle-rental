package rental.service;

import rental.dao.BranchDao;
import rental.model.Branch;

import java.util.List;

public class BranchServiceImpl implements BranchService{

    private final BranchDao branchDao;

    public BranchServiceImpl(BranchDao branchDao){
        this.branchDao = branchDao;
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

    @Override
    public void removeBranch(String branchName){
        branchDao.removeBranch(branchName);
    }
}
