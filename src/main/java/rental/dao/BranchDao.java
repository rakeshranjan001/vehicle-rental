package rental.dao;

import rental.model.Branch;

import java.util.HashMap;
import java.util.Map;

public class BranchDao {
    private static BranchDao branchDao;
    private Map<String, Branch> branchMap;

    private BranchDao(){
        this.branchMap = new HashMap<>();
    }

    public static BranchDao getInstance(){
        if(branchDao == null){
            branchDao = new BranchDao();
        }
        return branchDao;
    }

    public void addBranch(Branch branch) throws Exception {
        if(branchMap.containsKey(branch.getId())){
            throw new Exception("Branch name already present !!");
        }
        branchMap.put(branch.getId(), branch);
    }

    public Branch getBranch(String branchId){
        return branchMap.get(branchId);
    }
}
