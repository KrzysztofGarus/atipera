package pl.wannabe.atipera.service;

/**
 * This interface represents a variable provider that retrieves environment variables.
 */
public interface VariableProvider {

    /**
     * Retrieves the value of the specified environment variable.
     *
     * @param name the name of the environment variable
     * @return the value of the environment variable, or null if it is not found
     */
    String getEnvironmentVariable(String name);
    
}
