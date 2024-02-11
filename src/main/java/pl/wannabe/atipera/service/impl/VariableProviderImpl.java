package pl.wannabe.atipera.service.impl;

import org.springframework.stereotype.Service;

import pl.wannabe.atipera.service.VariableProvider;

@Service
public class VariableProviderImpl implements VariableProvider {

    @Override
    public String getEnvironmentVariable(String name) {
        return System.getenv(name);
    }
}
