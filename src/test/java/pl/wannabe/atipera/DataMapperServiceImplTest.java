package pl.wannabe.atipera;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;
import pl.wannabe.atipera.service.impl.DataMapperServiceImpl;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class DataMapperServiceImplTest {

    @Autowired
    private DataMapperServiceImpl dataMapperService;

    @Test
    public void filterAndMapRawDataTest() throws IOException, URISyntaxException {

        String rawGitHubReposData = Files
                .readString(Paths.get(getClass().getResource("/RawUserRepoListCorrect.json").toURI()));
        Mono<String> rawGitHubReposDataMono = Mono.just(rawGitHubReposData);

        Mono<List<UserRepoDetails>> mappedDetails = dataMapperService.filterAndMapRawData(rawGitHubReposDataMono);

        StepVerifier.create(mappedDetails)
                .expectNextMatches(repositoryDataResponses -> {
                    return repositoryDataResponses.get(0).name().equals("algorithmic-complexity") &&
                            repositoryDataResponses.get(0).owner().login().equals("KrzysztofGarus");
                })
                .verifyComplete();
    }

    @Test
    public void mapRawDataToBranchesDetailsTest() throws IOException, URISyntaxException {

        String rawGitHubBrancesData = Files
                .readString(Paths.get(getClass().getResource("/RawUserRepoDetailsCorrect.json").toURI()));
        Mono<String> rawGitHubBranchesDataMono = Mono.just(rawGitHubBrancesData);

        Mono<List<UserRepoBranchesDetails>> mappedBranchesDetails = dataMapperService
                .mapRawDataToBranchesDetails(rawGitHubBranchesDataMono);

        StepVerifier.create(mappedBranchesDetails)
                .expectNextMatches(UserRepoBranchesDetails -> {
                    return UserRepoBranchesDetails.get(0).commit().sha()
                            .equals("efa1b5550658acfb786354f3a758d40986a1a3d5") &&
                            UserRepoBranchesDetails.get(0).name().equals("main");
                })
                .verifyComplete();
    }

}
