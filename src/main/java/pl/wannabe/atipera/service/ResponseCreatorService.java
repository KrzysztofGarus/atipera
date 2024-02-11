package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.BranchesDataResponse;
import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;

public interface ResponseCreatorService {

    List<RepositoryDataResponse> createRepositoryDataResponse(List<UserRepoDetails> userReposDetails);

    List<BranchesDataResponse> createBranchesDataResponse(List<UserRepoBranchesDetails> branchesDetailsList);

}
