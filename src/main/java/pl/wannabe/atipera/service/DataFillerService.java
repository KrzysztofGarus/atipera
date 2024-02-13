package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.RepositoryDataResponse;
import reactor.core.publisher.Mono;

/**
 * This interface represents a service for filling repository data with branches data.
 */
public interface DataFillerService {

    /**
     * Fills the given repository data with branches data.
     *
     * @param repositoryDataResponse The repository data to be filled with branches data.
     * @return A Mono emitting the filled repository data.
     */
    Mono<List<RepositoryDataResponse>> fillWithBranchesData(Mono<List<RepositoryDataResponse>> repositoryDataResponse);
    
}
