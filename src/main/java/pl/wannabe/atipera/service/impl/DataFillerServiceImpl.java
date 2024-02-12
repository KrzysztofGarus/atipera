package pl.wannabe.atipera.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.wannabe.atipera.dto.BranchesDataResponse;
import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.service.DataFillerService;
import pl.wannabe.atipera.service.DataMapperService;
import pl.wannabe.atipera.service.GitHubApiService;
import pl.wannabe.atipera.service.ResponseCreatorService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DataFillerServiceImpl implements DataFillerService {

    private final DataMapperService dataMapperService;
    private final GitHubApiService gitHubApiService;
    private final ResponseCreatorService responseCreatorService;

    @Override
public Mono<List<RepositoryDataResponse>> fillWithBranchesData(Mono<List<RepositoryDataResponse>> repositoryDataResponse) {
    return repositoryDataResponse.flatMapMany(Flux::fromIterable)
            .flatMap(repository -> {
                Mono<String> rawBranchesData = gitHubApiService.getRawBranchesData(repository.ownerLogin(), repository.repositoryName());
                return dataMapperService.mapRawDataToBranchesDetails(rawBranchesData)
                        .flatMap(branchesDetails -> {
                            List<BranchesDataResponse> branchesDataResponses = responseCreatorService.createBranchesDataResponse(branchesDetails);
                            repository.branches().addAll(branchesDataResponses);
                            return Mono.just(repository);
                        });
            })
            .collectList();
}
}
