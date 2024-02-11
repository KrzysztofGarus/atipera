package pl.wannabe.atipera.service;

import reactor.core.publisher.Mono;

public interface GitHubApiService {

    Mono<String> getRawUserRepoList(String ownerLogin);

    Mono<String> getRawBranchesData(String ownerLogin, String repositoryName);

}