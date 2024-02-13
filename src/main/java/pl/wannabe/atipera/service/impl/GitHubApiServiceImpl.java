package pl.wannabe.atipera.service.impl;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;

import pl.wannabe.atipera.service.GitHubApiService;
import pl.wannabe.atipera.service.VariableProvider;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GitHubApiServiceImpl implements GitHubApiService {

    private String GITHUB_USER_REPOS_URL = "https://api.github.com/users/%s/repos?per_page=%s";
    private String GITHUB_USER_REPO_BRANCHES_DETAILS_URL = "https://api.github.com/repos/%s/%s/branches?per_page=%s";
    private int PER_PAGE = 100;

    private final WebClient webClient;
    private final VariableProvider VariableProvider;

    @Override
    public Mono<String> getRawUserRepoList(String ownerLogin) {

        String gitHubToken = VariableProvider.getEnvironmentVariable("githubtoken");

        return webClient.get()
                .uri(String.format(GITHUB_USER_REPOS_URL, ownerLogin, PER_PAGE))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + gitHubToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

    @Override
    public Mono<String> getRawBranchesData(String ownerLogin, String repositoryName) {

        String gitHubToken = VariableProvider.getEnvironmentVariable("githubtoken");

        return webClient.get()
                .uri(String.format(GITHUB_USER_REPO_BRANCHES_DETAILS_URL, ownerLogin, repositoryName, PER_PAGE))
                .header(HttpHeaders.AUTHORIZATION, "Bearer" + gitHubToken)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

}
