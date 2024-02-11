package pl.wannabe.atipera.dto;

public record UserRepoDetails(
                String name,
                String fork,
                Owner owner) {
        
        public record Owner(String login) {
        }
}