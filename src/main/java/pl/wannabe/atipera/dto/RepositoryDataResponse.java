package pl.wannabe.atipera.dto;

import java.util.List;

public record RepositoryDataResponse(

                String repositoryName,
                String ownerLogin,
                List<BranchesDataResponse> branches) {
}
