package pl.wannabe.atipera.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.dto.UserRepoDetails;
import pl.wannabe.atipera.service.DataFillerService;
import pl.wannabe.atipera.service.DataMapperService;
import pl.wannabe.atipera.service.GitHubApiService;
import pl.wannabe.atipera.service.ResponseCreatorService;
import pl.wannabe.atipera.service.UserRepositoryService;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final GitHubApiService gitHubApiService;
    private final DataMapperService dataMapperService;
    private final ResponseCreatorService responseCreatorService;
    private final DataFillerService dataFillerService;

    @Override
    public Mono<List<RepositoryDataResponse>> getUserReposDetails(String ownerLogin) {
        Mono<String> rawUserRepoList = gitHubApiService.getRawUserRepoList(ownerLogin);
        Mono<List<UserRepoDetails>> userRepoDetails = dataMapperService.filterAndMapRawData(rawUserRepoList);
        Mono<List<RepositoryDataResponse>> repositoryDataResponse = responseCreatorService
                .createRepositoryDataResponse(userRepoDetails);
        return dataFillerService.fillWithBranchesData(repositoryDataResponse);
    }

}
