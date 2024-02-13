package pl.wannabe.atipera;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.service.DataMapperService;
import pl.wannabe.atipera.service.GitHubApiService;
import pl.wannabe.atipera.service.ResponseCreatorService;
import pl.wannabe.atipera.service.impl.DataFillerServiceImpl;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class DataFillerServiceImplTest {

    @Autowired
    private DataMapperService dataMapperService;

    @Mock
    private GitHubApiService gitHubApiService;

    @Autowired
    private ResponseCreatorService responseCreatorService;

    @InjectMocks
    private DataFillerServiceImpl dataFillerService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        dataFillerService = new DataFillerServiceImpl(dataMapperService, gitHubApiService, responseCreatorService);
    }

    @Test
    public void FillWithBranchesDataTest() throws IOException, URISyntaxException {

        String rawBranchesData = Files
                .readString(Paths.get(getClass().getResource("/RawUserRepoDetailsCorrect.json").toURI()));
        Mono<String> rawBranchesDataMono = Mono.just(rawBranchesData);

        RepositoryDataResponse repositoryDataResponse = new RepositoryDataResponse("algorithmic-complexity",
                "krzysztofgarus", new ArrayList<>());
        List<RepositoryDataResponse> repositoryDataResponseList = new ArrayList<>();
        repositoryDataResponseList.add(repositoryDataResponse);
        Mono<List<RepositoryDataResponse>> repositoryDataResponseMono = Mono.just(repositoryDataResponseList);

        when(gitHubApiService.getRawBranchesData("krzysztofgarus", "algorithmic-complexity"))
                .thenReturn(rawBranchesDataMono);

        StepVerifier.create(dataFillerService.fillWithBranchesData(repositoryDataResponseMono))
                .expectNextMatches(repositoryDataResponses -> {
                    return repositoryDataResponses.get(0).branches().size() == 1;
                })
                .verifyComplete();

    }
}