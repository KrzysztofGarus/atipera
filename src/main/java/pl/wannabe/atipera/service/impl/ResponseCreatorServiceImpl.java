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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ResponseCreatorServiceImpl implements ResponseCreatorService {

    @Override
public Mono<List<RepositoryDataResponse>> createRepositoryDataResponse(Mono<List<UserRepoDetails>> userRepoDetails) {
    return userRepoDetails.flatMapMany(Flux::fromIterable)
            .map(detail -> new RepositoryDataResponse(detail.name(), detail.owner().login(), new ArrayList<>()))
            .collectList();
}

    @Override
    public List<BranchesDataResponse> createBranchesDataResponse(List<UserRepoBranchesDetails> branchesDetailsList) {
        return branchesDetailsList.stream()
                .map(detail -> new BranchesDataResponse(detail.name(), detail.commit().sha()))
                .collect(Collectors.toList());
    }
}
