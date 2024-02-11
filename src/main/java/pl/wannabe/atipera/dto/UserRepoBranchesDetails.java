package pl.wannabe.atipera.dto;

public record UserRepoBranchesDetails(
        String name,
        Commit commit) {

    public record Commit(String sha) {
    }
}
