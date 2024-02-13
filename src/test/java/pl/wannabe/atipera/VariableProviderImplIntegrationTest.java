package pl.wannabe.atipera;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import pl.wannabe.atipera.service.impl.VariableProviderImpl;

import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Integration test class for VariableProviderImpl.
 */
@SpringBootTest
public class VariableProviderImplIntegrationTest {

    @Autowired
    private VariableProviderImpl variableProvider;

    @Test
    public void testGetNonExistingEnvironmentVariable() {

        String value = variableProvider.getEnvironmentVariable("variableName");

        assertNull(value, "The value of a non-existing environment variable should be null");
    }
}
