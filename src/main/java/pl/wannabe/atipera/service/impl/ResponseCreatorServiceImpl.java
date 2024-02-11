package pl.wannabe.atipera.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import pl.wannabe.atipera.dto.BranchesDataResponse;
import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;
import pl.wannabe.atipera.service.ResponseCreatorService;

@Service
public class ResponseCreatorServiceImpl implements ResponseCreatorService {

    @Override
    public List<RepositoryDataResponse> createRepositoryDataResponse(List<UserRepoDetails> userRepoDetails) {
        return userRepoDetails.stream()
                .map(detail -> new RepositoryDataResponse(detail.name(), detail.owner().login(), new ArrayList<>()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BranchesDataResponse> createBranchesDataResponse(List<UserRepoBranchesDetails> branchesDetailsList) {
        return branchesDetailsList.stream()
                .map(detail -> new BranchesDataResponse(detail.name(), detail.commit().sha()))
                .collect(Collectors.toList());
    }
}
