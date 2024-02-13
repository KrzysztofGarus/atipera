package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.RepositoryDataResponse;
import reactor.core.publisher.Mono;

/**
 * This interface represents a service for retrieving repository details for a user.
 */
public interface UserRepositoryService {

    /**
     * Retrieves the details of repositories owned by a user.
     *
     * @param ownerLogin the login of the user
     * @return a Mono emitting a list of RepositoryDataResponse objects
     */
    Mono<List<RepositoryDataResponse>> getUserReposDetails(String ownerLogin);

}
