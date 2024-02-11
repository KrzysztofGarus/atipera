package pl.wannabe.atipera.service;

import java.util.List;

import pl.wannabe.atipera.dto.RepositoryDataResponse;

public interface DataFillerService {

    List<RepositoryDataResponse> fillWithBranchesData(List<RepositoryDataResponse> repositoryDataResponse);
    
}
