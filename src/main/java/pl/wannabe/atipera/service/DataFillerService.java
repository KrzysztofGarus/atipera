package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.RepositoryDataResponse;
import reactor.core.publisher.Mono;

public interface DataFillerService {

    Mono<List<RepositoryDataResponse>> fillWithBranchesData(Mono<List<RepositoryDataResponse>> repositoryDataResponse);
    
}
