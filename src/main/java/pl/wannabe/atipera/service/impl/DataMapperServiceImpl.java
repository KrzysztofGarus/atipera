package pl.wannabe.atipera.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import lombok.RequiredArgsConstructor;
import pl.wannabe.atipera.dto.UserRepoBranchesDetails;
import pl.wannabe.atipera.dto.UserRepoDetails;
import pl.wannabe.atipera.service.DataMapperService;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DataMapperServiceImpl implements DataMapperService {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<List<UserRepoDetails>> filterAndMapRawData(Mono<String> rawGitHubData) {
        return rawGitHubData.flatMap(data -> {
            try {
                List<UserRepoDetails> details = objectMapper.readValue(data,
                        TypeFactory.defaultInstance().constructCollectionType(List.class, UserRepoDetails.class));
                List<UserRepoDetails> filteredDetails = details.stream()
                        .filter(detail -> !detail.fork().equals("true"))
                        .collect(Collectors.toList());
                return Mono.just(filteredDetails);
            } catch (JsonProcessingException e) {
                return Mono.error(e);
            } catch (Exception e) {
                return Mono.error(e);
            }
        });
    }

    @Override
    public Mono<List<UserRepoBranchesDetails>> mapRawDataToBranchesDetails(Mono<String> rawBranchesData) {
        return rawBranchesData.flatMap(data -> {
            try {
                List<UserRepoBranchesDetails> details = objectMapper.readValue(data, TypeFactory.defaultInstance()
                        .constructCollectionType(List.class, UserRepoBranchesDetails.class));
                return Mono.just(details);
            } catch (JsonProcessingException e) {
                return Mono.error(e);
            } catch (Exception e) {
                return Mono.error(e);
            }
        });
    }

}
