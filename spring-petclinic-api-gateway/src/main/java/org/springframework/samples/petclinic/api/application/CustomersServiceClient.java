package org.springframework.samples.petclinic.api.application;

import static java.util.Arrays.asList;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.List;

/**
 * @author Maciej Szarlinski
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomersServiceClient {

    private final RestTemplate loadBalancedRestTemplate;

    public OwnerDetails getOwner(final int ownerId) {
        return loadBalancedRestTemplate.getForObject("http://customers-service/owners/{ownerId}", OwnerDetails.class, ownerId);
    }

    @HystrixCommand(fallbackMethod = "getOwnersFallback")
    public OwnersList getOwners() {
        final ResponseEntity<List<OwnerDescriptor>> response = loadBalancedRestTemplate.exchange("http://customers-service/owners",
            HttpMethod.GET, null, new ParameterizedTypeReference<List<OwnerDescriptor>>() {});
        return OwnersList.of(response.getBody());
    }

    public OwnersList getOwnersFallback() {
        return OwnersList.failure();
    }
}
