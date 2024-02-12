package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.RepositoryDataResponse;
import reactor.core.publisher.Mono;

public interface UserRepositoryService {

    Mono<List<RepositoryDataResponse>> getUserReposDetails(String ownerLogin);

}
