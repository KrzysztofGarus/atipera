package pl.wannabe.atipera.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.service.UserRepositoryService;
import reactor.core.publisher.Mono;

/**
 * Controller for handling GitHub-related requests.
 */
@RestController
@RequiredArgsConstructor
public class GitHubController {

    private final UserRepositoryService userRepositoryService;

    /**
     * Retrieves the details of repositories owned by a user.
     *
     * @param ownerLogin the login of the owner of the repositories
     * @return a ResponseEntity containing a Mono of a list of
     *         RepositoryDataResponse objects
     */
    @GetMapping("/repositories/{ownerLogin}")
    public ResponseEntity<Mono<List<RepositoryDataResponse>>> getUserReposDetails(@PathVariable String ownerLogin) {
        return ResponseEntity.ok(userRepositoryService.getUserReposDetails(ownerLogin));
    }
}
