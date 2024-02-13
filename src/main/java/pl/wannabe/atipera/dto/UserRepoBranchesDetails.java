package pl.wannabe.atipera.dto;

/**
 * Represents the details of a user repository branch.
 */
public record UserRepoBranchesDetails(
        String name,
        Commit commit) {

    /**
     * Represents the commit details of a user repository branch.
     */
    public record Commit(String sha) {
    }
}
