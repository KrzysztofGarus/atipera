package pl.wannabe.atipera;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import pl.wannabe.atipera.dto.BranchesDataResponse;
import pl.wannabe.atipera.dto.RepositoryDataResponse;
import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;
import pl.wannabe.atipera.service.impl.ResponseCreatorServiceImpl;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * This class contains tests for the ResponseCreatorServiceImpl class.
 */
@SpringBootTest
class ResponseCreatorServiceImplTest {

    private final ResponseCreatorServiceImpl service = new ResponseCreatorServiceImpl();

    @Test
    void testCreateRepositoryDataResponse() {

        UserRepoDetails.Owner owner = new UserRepoDetails.Owner("testUser");
        UserRepoDetails detail = new UserRepoDetails("testRepo", "false", owner);

        Mono<List<UserRepoDetails>> userRepoDetails = Mono.just(Arrays.asList(detail));

        Mono<List<RepositoryDataResponse>> result = service.createRepositoryDataResponse(userRepoDetails);

        StepVerifier.create(result)
                .assertNext(list -> {
                    assertEquals(1, list.size());
                    assertEquals("testRepo", list.get(0).repositoryName());
                    assertEquals("testUser", list.get(0).ownerLogin());
                })
                .verifyComplete();

    }

    @Test
    void testCreateBranchesDataResponse() {

        UserRepoBranchesDetails.Commit commit = new UserRepoBranchesDetails.Commit("testSha");
        UserRepoBranchesDetails branch = new UserRepoBranchesDetails("testName", commit);

        List<UserRepoBranchesDetails> branchesDetailsList = Arrays.asList(branch);
        List<BranchesDataResponse> result = service.createBranchesDataResponse(branchesDetailsList);

        assertEquals(1, result.size());
        assertEquals("testName", result.get(0).name());
        assertEquals("testSha", result.get(0).sha());

    }

}
