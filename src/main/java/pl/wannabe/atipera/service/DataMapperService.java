package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;
import reactor.core.publisher.Mono;

/**
 * This interface represents a service responsible for mapping raw data to specific domain objects.
 */
public interface DataMapperService {

    /**
     * Filters and maps raw GitHub data to a list of UserRepoDetails objects.
     *
     * @param rawGitHubData the raw GitHub data to be processed
     * @return a Mono emitting a list of UserRepoDetails objects
     */
    Mono<List<UserRepoDetails>> filterAndMapRawData(Mono<String> rawGitHubData);

    /**
     * Maps raw branches data to a list of UserRepoBranchesDetails objects.
     *
     * @param rawBranchesData the raw branches data to be processed
     * @return a Mono emitting a list of UserRepoBranchesDetails objects
     */
    Mono<List<UserRepoBranchesDetails>> mapRawDataToBranchesDetails(Mono<String> rawBranchesData);

}
