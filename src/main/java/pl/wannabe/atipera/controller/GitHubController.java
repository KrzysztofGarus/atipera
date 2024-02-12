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


@RestController
@RequiredArgsConstructor
public class GitHubController {

    private final UserRepositoryService userRepositoryService;

    @GetMapping("/repositories/{ownerLogin}")
    public ResponseEntity<Mono<List<RepositoryDataResponse>>> getUserReposDetails(@PathVariable String ownerLogin) {
        return ResponseEntity.ok(userRepositoryService.getUserReposDetails(ownerLogin));
    }
}
