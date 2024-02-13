package pl.wannabe.atipera.dto;

/**
 * Represents the details of a user repository.
 */
public record UserRepoDetails(
                String name,
                String fork,
                Owner owner) {
        
        public record Owner(String login) {
        }
}