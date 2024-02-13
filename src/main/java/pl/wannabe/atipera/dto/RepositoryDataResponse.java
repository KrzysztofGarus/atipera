package pl.wannabe.atipera.dto;

import java.util.List;

/**
 * Represents a response containing repository data.
 */
public record RepositoryDataResponse(

                String repositoryName,
                String ownerLogin,
                List<BranchesDataResponse> branches) {
}
