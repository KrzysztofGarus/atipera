package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;
import reactor.core.publisher.Mono;

public interface DataMapperService {

    List<UserRepoDetails> filterAndMapRawData(Mono<String> rawGitHubData);

    Mono<List<UserRepoBranchesDetails>> mapRawDataToBranchesDetails(Mono<String> rawBranchesData);

}
