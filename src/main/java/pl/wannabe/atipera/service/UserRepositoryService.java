package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.RepositoryDataResponse;

public interface UserRepositoryService {

    List<RepositoryDataResponse> getUserReposDetails(String ownerLogin);

}
