package pl.wannabe.atipera;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.reflect.Field;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;

import pl.wannabe.atipera.service.impl.GitHubApiServiceImpl;
import pl.wannabe.atipera.service.impl.VariableProviderImpl;
import reactor.test.StepVerifier;

/**
 * This class contains tests for the GitHubApiServiceImpl class.
 */
@SpringBootTest
public class GitHubApiServiceImplTest {

    private WireMockServer wireMockServer;
    private GitHubApiServiceImpl gitHubService;
    private VariableProviderImpl mockProvider;

    @BeforeEach
    public void setup()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        wireMockServer = new WireMockServer(WireMockConfiguration.options().port(8080));
        wireMockServer.start();
        mockProvider = mock(VariableProviderImpl.class);
        when(mockProvider.getEnvironmentVariable("githubtoken")).thenReturn("test_token");

        WebClient webClient = WebClient.create();
        gitHubService = new GitHubApiServiceImpl(webClient, mockProvider);

        Field repos = GitHubApiServiceImpl.class.getDeclaredField("GITHUB_USER_REPOS_URL");
        repos.setAccessible(true);
        repos.set(gitHubService, "http://localhost:" + wireMockServer.port() + "/users/%s/repos?per_page=%s");

        Field fieldBranches = GitHubApiServiceImpl.class.getDeclaredField("GITHUB_USER_REPO_BRANCHES_DETAILS_URL");
        fieldBranches.setAccessible(true);
        fieldBranches.set(gitHubService,
                "http://localhost:" + wireMockServer.port() + "/repos/%s/%s/branches?per_page=%s");

        Field perPage = GitHubApiServiceImpl.class.getDeclaredField("PER_PAGE");
        perPage.setAccessible(true);
        perPage.set(gitHubService, 100);

    }

    @AfterEach
    public void teardown() {
        wireMockServer.stop();
    }

    @Test
    public void testGetRawGitHubUserRepoDetails_Succes() throws IOException, URISyntaxException {

        String owner = "test_owner";

        String expectedResponse = Files
                .readString(Paths.get(getClass().getResource("/RawUserRepoListCorrect.json").toURI()));

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/users/" + owner + "/repos?per_page=100"))
                .withHeader("Authorization", WireMock.equalTo("Bearer test_token"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(expectedResponse)));

        StepVerifier.create(gitHubService.getRawUserRepoList(owner))
                .expectNext(expectedResponse)
                .verifyComplete();
    }

    @Test
    public void testGetRawGitHubUserRepoDetails_Unauthorized() throws IOException, URISyntaxException {

        String owner = "test_owner";

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/users/" + owner + "/repos?per_page=100"))
                .withHeader("Authorization", WireMock.equalTo("Bearer test_token"))
                .willReturn(aResponse()
                        .withStatus(401)));

        StepVerifier.create(gitHubService.getRawUserRepoList(owner))
                .expectError(WebClientResponseException.class)
                .verify();
    }

    @Test
    public void testGetRawGitHubUserRepoDetails_NotFound() throws IOException, URISyntaxException {

        String owner = "test_owner";
        int perPage = 100;

        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/users/" + owner + "/repos?per_page=" + perPage))
                .withHeader("Authorization", WireMock.equalTo("Bearer test_token"))
                .willReturn(aResponse()
                        .withStatus(404)));

        StepVerifier.create(gitHubService.getRawUserRepoList(owner))
                .expectError(WebClientResponseException.class)
                .verify();
    }

}