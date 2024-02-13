package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.BranchesDataResponse;
import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;
import reactor.core.publisher.Mono;

/**
 * This interface defines the contract for a ResponseCreatorService, which is responsible for creating response objects
 * for repository data and branches data.
 */
public interface ResponseCreatorService {

    /**
     * Creates a response object for repository data based on the provided user repository details.
     *
     * @param userReposDetails The user repository details.
     * @return A Mono containing a list of RepositoryDataResponse objects.
     */
    Mono<List<RepositoryDataResponse>> createRepositoryDataResponse(Mono<List<UserRepoDetails>> userReposDetails);

    /**
     * Creates a list of response objects for branches data based on the provided user repository branches details.
     *
     * @param branchesDetailsList The user repository branches details.
     * @return A list of BranchesDataResponse objects.
     */
    List<BranchesDataResponse> createBranchesDataResponse(List<UserRepoBranchesDetails> branchesDetailsList);

}
