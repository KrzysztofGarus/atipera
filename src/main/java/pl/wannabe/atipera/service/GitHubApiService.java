package pl.wannabe.atipera.service;

import reactor.core.publisher.Mono;

/**
 * This interface represents a service for interacting with the GitHub API.
 */
public interface GitHubApiService {

    /**
     * Retrieves the raw user repository list for the specified owner login.
     *
     * @param ownerLogin the login of the owner
     * @return a Mono containing the raw user repository list
     */
    Mono<String> getRawUserRepoList(String ownerLogin);

    /**
     * Retrieves the raw branches data for the specified owner login and repository name.
     *
     * @param ownerLogin      the login of the owner
     * @param repositoryName  the name of the repository
     * @return a Mono containing the raw branches data
     */
    Mono<String> getRawBranchesData(String ownerLogin, String repositoryName);

}